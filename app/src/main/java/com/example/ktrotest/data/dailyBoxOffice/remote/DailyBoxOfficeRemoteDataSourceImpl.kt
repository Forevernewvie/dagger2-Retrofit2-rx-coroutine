package com.example.ktrotest.data.dailyBoxOffice.remote

import android.util.Log
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.network.HttpRequestByKtor
import kotlinx.coroutines.*
import javax.inject.Inject

class DailyBoxOfficeRemoteDataSourceImpl : DailyBoxOfficeRemoteDataSource {

    @Inject
    lateinit var ktor: HttpRequestByKtor

    override suspend fun fetchBoxOfficeData(targetDt: String, callback: OnBoxOfficeDataListener) {
        CoroutineScope(Dispatchers.IO ).launch {
            callback.success(ktor.requestKtorIo(targetDt))
        }
    }
}