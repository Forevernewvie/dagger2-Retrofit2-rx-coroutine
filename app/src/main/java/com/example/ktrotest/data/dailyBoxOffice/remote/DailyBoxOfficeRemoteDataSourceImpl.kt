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

    override fun remoteFetchBoxOfficeData(targetDt: String): Flow<List<DailyBoxOffice>> = flow {
            httpClient.get<OfficeResult>(Api.URL){
                method = HttpMethod.Get
                parameter("key",Api.KEY)
                parameter("targetDt",targetDt)
            }.run { emit(this.boxOfficeResult.dailyBoxOfficeList) }
    }
}