package com.example.ktrotest.model

import kotlinx.serialization.Serializable

@Serializable
data class OfficeResult(
    val boxOfficeResult: BoxOfficeResult
)