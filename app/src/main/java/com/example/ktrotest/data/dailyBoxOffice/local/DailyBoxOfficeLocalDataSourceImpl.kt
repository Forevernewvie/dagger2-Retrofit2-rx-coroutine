package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyBoxOfficeLocalDataSourceImpl @Inject constructor (
    private val boxOfficeDao: BoxOfficeDao
)
    : DailyBoxOfficeLocalDataSource {

    override suspend fun insert(boxOffice: DailyBoxOffice) {
        boxOfficeDao.insert(boxOffice)
    }

    override fun getBoxOffice(): Flow<List<DailyBoxOffice>> {
       return boxOfficeDao.getBoxOffice()
    }

    override suspend fun delete() {
        boxOfficeDao.delete()
    }

    override  fun getMovieName(): Flow<List<String>> {
        return boxOfficeDao.getMovieName()
    }
}