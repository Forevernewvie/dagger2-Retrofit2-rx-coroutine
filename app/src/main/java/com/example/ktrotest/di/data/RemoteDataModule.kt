package com.example.ktrotest.data.dailyBoxOffice.di.data

import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import com.example.ktrotest.network.HttpRequestByKtor
import com.example.ktrotest.network.KtorRequest
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun providesKtorInterface(httpRequestByKtor: HttpRequestByKtor,targetDt:String):KtorRequest{
        return httpRequestByKtor.requestBoxOfficeByKtor(targetDt)
    }


    @Provides
    @Reusable
    fun providesDailyBoxOfficeRemoteDataSource(ktorRequest: KtorRequest): DailyBoxOfficeRemoteDataSource {
        return DailyBoxOfficeRemoteDataSourceImpl(ktorRequest)
    }

}