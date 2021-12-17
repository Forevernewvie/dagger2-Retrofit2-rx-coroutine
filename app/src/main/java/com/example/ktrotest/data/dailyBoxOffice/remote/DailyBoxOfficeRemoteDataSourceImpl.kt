package com.example.ktrotest.data.dailyBoxOffice.remote

import android.util.Log
import com.example.ktrotest.Api
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import java.lang.reflect.Method
import javax.inject.Inject

class DailyBoxOfficeRemoteDataSourceImpl @Inject constructor
    (private val httpClient: HttpClient) : DailyBoxOfficeRemoteDataSource {

    override suspend fun fetchBoxOfficeData(targetDt: String, callback: OnBoxOfficeDataListener) {
        CoroutineScope(Dispatchers.IO ).launch {

            callback.success(httpClient.get(Api.url){
                method = HttpMethod.Get
                parameter("key",Api.key)
                parameter("targetDt",targetDt)
                }
            )
        }
    }
}