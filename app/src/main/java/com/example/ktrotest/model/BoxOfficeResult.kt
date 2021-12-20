package com.example.ktrotest.model

import kotlinx.serialization.Serializable
@Serializable
data class BoxOfficeResult(
    val dailyBoxOfficeList: List<DailyBoxOffice>,
)