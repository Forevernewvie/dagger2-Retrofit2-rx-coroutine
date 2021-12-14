package com.example.ktrotest.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyBoxOffice(
    val movieNm: String,
    val openDt: String,
    val rank: String,
)