package com.poulastaa.passekyapp.domain.repository

import com.poulastaa.passekyapp.data.model.User
import com.poulastaa.passekyapp.data.model.UserCreationResponse
import com.poulastaa.passekyapp.data.model.UserInfo

interface PasskeyRepository {
    suspend fun passkeyRequest(userInfo: UserInfo): String?
    suspend fun sendUserToServer(user: User): UserCreationResponse
    suspend fun getUserFromServer(id: String): User?
}