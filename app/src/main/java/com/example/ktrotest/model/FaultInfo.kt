package com.example.ktrotest.model

import kotlinx.serialization.Serializable

data class FaultInfo(
    @Serializable
    val errorCode:String,
    @Serializable
    val message:String
)
