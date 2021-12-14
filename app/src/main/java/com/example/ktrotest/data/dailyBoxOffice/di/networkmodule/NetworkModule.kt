package com.example.ktrotest.data.dailyBoxOffice.di.networkmodule

import com.example.ktrotest.network.HttpRequestByKtor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesKtor() = HttpRequestByKtor()
}