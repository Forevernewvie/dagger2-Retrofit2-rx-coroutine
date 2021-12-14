package com.example.ktrotest.model

import kotlinx.serialization.Serializable
@Serializable
data class BoxOfficeResult(
    val boxofficeType: String,
    val dailyBoxOfficeList: List<DailyBoxOffice>,
    val showRange: String
)