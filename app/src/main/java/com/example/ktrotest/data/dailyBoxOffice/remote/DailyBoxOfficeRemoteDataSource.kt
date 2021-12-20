package com.example.ktrotest.data.dailyBoxOffice.remote

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow

interface DailyBoxOfficeRemoteDataSource {
    fun fetchBoxOfficeData(targetDt:String): Flow<List<DailyBoxOffice>>
}
