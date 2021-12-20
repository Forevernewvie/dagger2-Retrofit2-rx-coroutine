package com.example.ktrotest.data.dailyBoxOffice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.model.DailyBoxOffice


@Database(entities = [DailyBoxOffice::class], version = 1)
abstract class BoxOfficeDataBase :RoomDatabase() {
    abstract fun boxOfficeDao() : BoxOfficeDao
}