package com.example.ktrotest.data.dailyBoxOffice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.room.BoxOffice


@Database(entities = [BoxOffice::class], version = 1)
abstract class BoxOfficeDataBase :RoomDatabase() {
    abstract fun boxOfficeDao() : BoxOfficeDao
}