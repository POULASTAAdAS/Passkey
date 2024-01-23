package com.poulastaa.passekyapp.data.repository

import android.util.Log
import androidx.credentials.CredentialManager
import com.google.gson.Gson
import com.poulastaa.passekyapp.data.model.GetUserRequest
import com.poulastaa.passekyapp.data.model.User
import com.poulastaa.passekyapp.data.model.UserCreationResponse
import com.poulastaa.passekyapp.data.model.UserInfo
import com.poulastaa.passekyapp.data.remote.PasskeyApi
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import javax.inject.Inject

class PasskeyRepositoryImpl @Inject constructor(
    private val api: PasskeyApi,
) : PasskeyRepository {
    override suspend fun passkeyRequest(userInfo: UserInfo): String? {
        return try {
            val response = api.passkeyReq(request = userInfo).string()

            Log.d("rawResponse", response)

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun sendUserToServer(user: User): UserCreationResponse {
        return try {
            api.createUser(user)
        } catch (e: Exception) {
            UserCreationResponse(
                status = false
            )
        }
    }

    override suspend fun getUserFromServer(id: String): User? {
        return try {
            api.getUser(
                request = GetUserRequest(
                    id = id
                )
            )
        } catch (e: Exception) {
            null
        }
    }


}