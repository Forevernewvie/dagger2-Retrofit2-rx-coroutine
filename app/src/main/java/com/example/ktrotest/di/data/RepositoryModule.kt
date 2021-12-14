package com.example.ktrotest.data.dailyBoxOffice.di.data

import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepositoryImpl
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun providesDailyBoxOfficeRepository(
        dailyBoxOfficeRemoteDataSource: DailyBoxOfficeRemoteDataSource,
        dailyBoxOfficeLocalDataSource: DailyBoxOfficeLocalDataSource
    ): DailyBoxOfficeRepository=
        DailyBoxOfficeRepositoryImpl(dailyBoxOfficeRemoteDataSource,dailyBoxOfficeLocalDataSource)

}