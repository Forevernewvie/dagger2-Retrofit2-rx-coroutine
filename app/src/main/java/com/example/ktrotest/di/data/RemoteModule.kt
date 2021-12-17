package com.example.ktrotest.di.data

import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesKtorClient() : HttpClient {
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer()
            }
        }
    }

    @Singleton
    @Provides
    fun providesBoxOfficeRemoteDataSource(httpClient: HttpClient):DailyBoxOfficeRemoteDataSource{
        return DailyBoxOfficeRemoteDataSourceImpl(httpClient)
    }

}