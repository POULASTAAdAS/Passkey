package com.poulastaa.passekyapp.data.remote

import com.poulastaa.passekyapp.data.model.UserInfo
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface PasskeyApi {
    @POST("/api/auth")
    suspend fun passkeyReq(
        @Body request: UserInfo
    ): ResponseBody
}