package com.example.ktrotest.di.data

import android.content.Context
import androidx.room.Room
import com.example.ktrotest.data.dailyBoxOffice.BoxOfficeDataBase
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context) : BoxOfficeDataBase{
        return Room.databaseBuilder(
            context,
            BoxOfficeDataBase::class.java,
            "BoxOfficeRoom.db"
        ).build()
    }

    @Reusable
    @Provides
    fun providesBoxOfficeDao(boxOfficeDataBase: BoxOfficeDataBase): BoxOfficeDao = boxOfficeDataBase.boxOfficeDao()

    @Singleton
    @Provides
    fun provideBoxOfficeLocalDataSource(boxOfficeDao: BoxOfficeDao): DailyBoxOfficeLocalDataSource{
        return DailyBoxOfficeLocalDataSourceImpl(boxOfficeDao)
    }

}