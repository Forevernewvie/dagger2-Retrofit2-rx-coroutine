package com.example.ktrotest.data.dailyBoxOffice.remote

import com.example.ktrotest.Api
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyBoxOfficeRemoteDataSourceImpl @Inject constructor
    (private val httpClient: HttpClient) : DailyBoxOfficeRemoteDataSource {

    override fun fetchBoxOfficeData(targetDt: String): Flow<List<DailyBoxOffice>> = flow {
            val result = httpClient.get<OfficeResult>(Api.url){
                method = HttpMethod.Get
                parameter("key",Api.key)
                parameter("targetDt",targetDt)
            }
        val data = result.boxOfficeResult.dailyBoxOfficeList
        // data 발행/생성
        emit(data.filter {
            it.rank.toInt() <=5
        })
    }
}