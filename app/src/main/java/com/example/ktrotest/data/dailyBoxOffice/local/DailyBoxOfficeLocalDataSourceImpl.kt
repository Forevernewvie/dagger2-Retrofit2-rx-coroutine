package com.example.ktrotest.data.dailyBoxOffice.local

import android.content.Context
import com.example.ktrotest.data.dailyBoxOffice.BoxOfficeDataBase
import com.example.ktrotest.room.BoxOffice
import javax.inject.Inject

class DailyBoxOfficeLocalDataSourceImpl @Inject constructor (
    private val boxOfficeDao: BoxOfficeDao
)
    : DailyBoxOfficeLocalDataSource {

    override suspend fun insert(boxOffice: BoxOffice) {
        boxOfficeDao.insert(boxOffice)
    }

    override suspend fun getBoxOffice(): List<BoxOffice> {
       return boxOfficeDao.getBoxOffice()
    }

    override suspend fun delete() {
        boxOfficeDao.delete()
    }

    override suspend fun getMovieName(): List<String> {
        return boxOfficeDao.getMovieName()
    }
}