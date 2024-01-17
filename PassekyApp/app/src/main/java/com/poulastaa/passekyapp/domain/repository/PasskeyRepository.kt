package com.poulastaa.passekyapp.domain.repository

import com.poulastaa.passekyapp.data.model.UserInfo

interface PasskeyRepository {
    suspend fun passkeyRequest(userInfo: UserInfo): String?
}