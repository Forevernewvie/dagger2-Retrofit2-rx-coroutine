package com.example.ktrotest.di.data

import com.example.ktrotest.Api
import com.example.ktrotest.model.OfficeResult
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    suspend fun providesHttpInterface(targetDt:String):OfficeResult{
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer(){
                    setPrettyPrinting()
                    setLenient()
                }
            }
        }.get(Api.url){
            method = HttpMethod.Get
            parameter("key",Api.key)
            parameter("targetDt",targetDt)
        }
    }
}