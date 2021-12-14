package com.example.ktrotest.network

import android.util.Log
import com.example.ktrotest.model.OfficeResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*


class HttpRequestByKtor {

    private val client:HttpClient = HttpClient(CIO){
        install(JsonFeature){
            GsonSerializer(){
                setPrettyPrinting()
                setLenient()
            }
        }
    }

    companion object{
        private const val key ="154bc1716d28fb8a212340401c409a10"
        const val url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
    }


    suspend fun requestKtorIo(targetDt:String): OfficeResult {

       return client.get(url) {
            method = HttpMethod.Get
            parameter("key", key)
            parameter("targetDt", targetDt)
        }
    }
}