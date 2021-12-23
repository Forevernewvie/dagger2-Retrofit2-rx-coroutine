package com.example.ktrotest.data.dailyBoxOffice.remote

import com.example.ktrotest.util.Api
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class DailyBoxOfficeRemoteDataSourceImpl @Inject constructor
    (private val httpClient: HttpClient) : DailyBoxOfficeRemoteDataSource {

    override suspend fun remoteFetchBoxOfficeData(targetDt: String): List<DailyBoxOffice> {
        return httpClient.get<OfficeResult>(Api.URL) {
            method = HttpMethod.Get
            parameter("key", Api.KEY)
            parameter("targetDt", targetDt)
        }.boxOfficeResult.dailyBoxOfficeList
    }
}