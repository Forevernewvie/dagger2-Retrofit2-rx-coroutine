package com.example.ktrotest.data.dailyBoxOffice.di.data

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

@Module  //이 의존성을 주입하겠다! 하는 함수를 만드는곳?
class LocalDataModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Context) : BoxOfficeDataBase{
        return Room.databaseBuilder(
            context,
            BoxOfficeDataBase::class.java,
            "BoxOfficeDB"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBoxOfficeDao(roomDatabase: BoxOfficeDataBase): BoxOfficeDao = roomDatabase.boxOfficeDao()

    @Provides
    @Reusable
    fun providesDailyBoxOfficeLocalDataSource(boxOfficeDao: BoxOfficeDao) : DailyBoxOfficeLocalDataSource{
        return DailyBoxOfficeLocalDataSourceImpl(boxOfficeDao)
    }

}