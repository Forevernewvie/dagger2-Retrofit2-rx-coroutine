package com.example.ktrotest.data.dailyBoxOffice.remote

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow

interface DailyBoxOfficeRemoteDataSource {
    suspend fun remoteFetchBoxOfficeData(targetDt:String): List<DailyBoxOffice>
}
