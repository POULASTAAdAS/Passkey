package com.poulastaa.passekyapp.presentation.screen.auth

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.PublicKeyCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.poulastaa.passekyapp.data.model.AuthState
import com.poulastaa.passekyapp.data.model.ClientData
import com.poulastaa.passekyapp.data.model.CreatePublicKeyCredentialResponse
import com.poulastaa.passekyapp.data.model.GetPublicKeyCredential
import com.poulastaa.passekyapp.data.model.User
import com.poulastaa.passekyapp.data.model.UserInfo
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import com.poulastaa.passekyapp.utils.Constants.ORIGIN
import com.poulastaa.passekyapp.utils.b64Decode
import com.poulastaa.passekyapp.utils.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val api: PasskeyRepository,
    private val credentialManager: CredentialManager,
    private val gson: Gson,
) : ViewModel() {
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Default)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    var email = mutableStateOf("poulastaadas2@gmail.com")
        private set

    var isLoading = mutableStateOf(false)
        private set

    private var challenge: String? = null

    fun onValueChange(value: String) {
        email.value = value
    }

    fun onDone(
        activity: Activity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val reqJson = passkeyReq()

            if (reqJson.isNotEmpty()) {
                val json = JsonParser().parse(reqJson).asJsonObject
                val type = json["type"].asString
                json.remove("type")

                // set challenge
                challenge = json["challenge"].asString.removeSuffix("==")

                Log.d("challenge", challenge.toString())

                if (type == "SignUp") {
                    createPasskey(json.toString(), activity)
                } else {
                    logIn(json.toString(), activity)
                }
            }
        }
    }

    private suspend fun passkeyReq(): String {
        isLoading.value = true

        return if (email.value.trim().validateEmail()) { // check email validity
            val result = api.passkeyRequest( // send passkey request to server
                userInfo = UserInfo(
                    email = email.value,
                    displayName = email.value.removeSuffix("@gmail.com")
                )
            )

            if (result == null) { // return null if no result--------------errors are logged before for simplicity
                _state.emit(AuthState.SignInFailure(message = "some error occurred"))
                isLoading.value = false

                return ""
            }

            isLoading.value = false
            result
        } else {
            isLoading.value = false
            _state.tryEmit(AuthState.NotValidEmail)
            ""
        }
    }

    private fun createPasskey(
        reqJson: String,
        activity: Activity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = credentialManager.createCredential(
                    context = activity,
                    request = CreatePublicKeyCredentialRequest(
                        requestJson = reqJson
                    )
                )

                val json =
                    (result as androidx.credentials.CreatePublicKeyCredentialResponse).registrationResponseJson
                val createPublicKeyCredentialResponse = gson.fromJson(
                    json,
                    CreatePublicKeyCredentialResponse::class.java
                )
                // check validity
                if (toClientData(
                        createPublicKeyCredentialResponse
                            .response.clientDataJSON
                    ).checkValidity()
                ) {
                    // create user and send to server
                    val response =
                        createPublicKeyCredentialResponse.toUser(
                            email = email.value.trim()
                        ).sendUserToServer()

                    if (response.status) {
                        //todo handle successful SignIn
                        println("SignedIn")
                    } else {
                        // Congratulations You are fucked
                    }
                } else {
                    // Congratulations You are fucked
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.tryEmit(AuthState.SignInFailure(message = e.message.toString()))
            }
        }
    }


    private fun logIn(
        resJson: String,
        activity: Activity
    ) {
        val option = GetPublicKeyCredentialOption(
            requestJson = resJson
        )

        val credentialRequest = GetCredentialRequest(
            credentialOptions = listOf(option),
        )

        isLoading.value = false

        viewModelScope.launch(Dispatchers.IO) {
            val response = credentialManager.getCredential(
                context = activity,
                request = credentialRequest
            ).credential as PublicKeyCredential

            val getPublicKeyCredential = gson.fromJson(
                response.authenticationResponseJson,
                GetPublicKeyCredential::class.java
            )
            // check validity
            if (toClientData(
                    getPublicKeyCredential.response
                        .clientDataJSON
                ).checkValidity()
            ) {
                val user = getPublicKeyCredential.getUser() // get user from server

                if (user == null) {
                    // Congratulations You are fucked
                    Log.d("GetPublicKeyCredential", "user null")
                    return@launch
                }

                // handle user login successful
                Log.d("user login", "success")
            }

        }
    }

    // ---------------------------------------------------------------------------------------------

    private suspend fun User.sendUserToServer() =
        api.sendUserToServer(this@sendUserToServer)

    private suspend fun GetPublicKeyCredential.getUser(): User? =
        api.getUserFromServer(id = this.id)

    private fun CreatePublicKeyCredentialResponse.toUser(email: String): User {
        return User(
            id = this.id,
            email = email
        )
    }

    private fun toClientData(clientDataJson: String): ClientData {
        val byteArray = clientDataJson.b64Decode()
        val json = String(byteArray, StandardCharsets.UTF_8)
        return Json.decodeFromString<ClientData>(json)
    }

    private fun ClientData.checkValidity(): Boolean {
        this@AuthViewModel.challenge?.let {
            return it == this.challenge && ORIGIN == this.origin
        }
        return false
    }
}