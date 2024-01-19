package com.poulastaa.passekyapp.presentation.screen.auth

import android.app.Activity
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CreatePublicKeyCredentialResponse
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.poulastaa.passekyapp.data.model.AuthState
import com.poulastaa.passekyapp.data.model.PasskeyResponse
import com.poulastaa.passekyapp.data.model.PublicKeyCredentialResponse
import com.poulastaa.passekyapp.data.model.UserInfo
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encode
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

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

    fun onValueChange(value: String) {
        email.value = value
    }

    fun onDone(
        activity: Activity
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val reqJson = passkeyReq()

            Log.d("reqJson", reqJson)

            if (reqJson.isNotEmpty())
                createPasskey(reqJson, activity)
        }
    }

    private suspend fun passkeyReq(): String {
        isLoading.value = true

        return if (validateEmail()) { // check email validity
            val result = api.passkeyRequest(
                userInfo = UserInfo(
                    email = email.value,
                    userName = email.value.removeSuffix("@gmail.com")
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

                val json = (result as CreatePublicKeyCredentialResponse).registrationResponseJson

                val passkeyResponse = gson.fromJson(
                    json,
                    PublicKeyCredentialResponse::class.java
                ).toPasskeyResponse()


            } catch (e: Exception) {
                e.printStackTrace()
                _state.tryEmit(AuthState.SignInFailure(message = e.message.toString()))
            }
        }
    }

    private fun validateEmail() = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()

    private fun PublicKeyCredentialResponse.toPasskeyResponse(): PasskeyResponse =
        PasskeyResponse( // todo encode then send
            id = this.id,
            rawId = this.rawId,
            type = this.type,
            attestationObject = this.response.attestationObject,
            authenticatorAttachment = this.authenticatorAttachment,
            clientDataJSON = this.response.clientDataJSON
        )


    @OptIn(ExperimentalEncodingApi::class)
    fun ByteArray.b64Encode(): String {
        return Base64.UrlSafe.encode(this)
    }
}