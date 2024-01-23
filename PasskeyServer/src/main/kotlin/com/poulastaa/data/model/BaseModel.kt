package com.poulastaa.data.model

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseModel(
    val type: String
)
