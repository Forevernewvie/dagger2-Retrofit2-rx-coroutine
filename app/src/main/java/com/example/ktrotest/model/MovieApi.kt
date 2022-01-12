package com.example.ktrotest.model

import com.example.ktrotest.util.Api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET(Api.URL) suspend fun fetchBoxOfficeMovieList(
        @Query("key") key:String,
        @Query("targetDt") targetDt:String
    ):OfficeResult

}