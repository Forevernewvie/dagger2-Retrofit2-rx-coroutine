package com.example.ktrotest.data.dailyBoxOffice.remote

import androidx.lifecycle.LiveData
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface DailyBoxOfficeRemoteDataSource {
    suspend fun remoteFetchBoxOfficeData(targetDt:String): OfficeResult
}
