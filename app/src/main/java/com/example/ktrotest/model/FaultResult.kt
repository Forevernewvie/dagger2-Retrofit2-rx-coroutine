package com.example.ktrotest.model

import kotlinx.serialization.Serializable

data class FaultResult(
    @Serializable
    val faultResult: FaultInfo
)
