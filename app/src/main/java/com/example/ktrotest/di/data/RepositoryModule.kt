package com.example.ktrotest.di.data

import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepositoryImpl
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesDailyBoxOfficeRepository(
        dailyBoxOfficeLocalDataSource: DailyBoxOfficeLocalDataSource,
        dailyBoxOfficeRemoteDataSource: DailyBoxOfficeRemoteDataSource
    ) : DailyBoxOfficeRepository {
        return DailyBoxOfficeRepositoryImpl(dailyBoxOfficeRemoteDataSource,dailyBoxOfficeLocalDataSource)
    }

}