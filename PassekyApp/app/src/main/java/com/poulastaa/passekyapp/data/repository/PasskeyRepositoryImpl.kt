package com.poulastaa.passekyapp.data.repository

import android.util.Log
import com.poulastaa.passekyapp.data.model.UserInfo
import com.poulastaa.passekyapp.data.remote.PasskeyApi
import com.poulastaa.passekyapp.domain.repository.PasskeyRepository
import javax.inject.Inject

class PasskeyRepositoryImpl @Inject constructor(
    private val api: PasskeyApi
) : PasskeyRepository {
    override suspend fun passkeyRequest(userInfo: UserInfo): String? {
        return try {
            val response = api.passkeyReq(request = userInfo).toString()

            Log.d("rawResponse", response)

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}