package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DailyBoxOfficeLocalDataSourceImpl @Inject constructor (
    private val boxOfficeDao: BoxOfficeDao
)
    : DailyBoxOfficeLocalDataSource {

    override suspend fun insert(boxOffice: DailyBoxOffice) {
        boxOfficeDao.insert(boxOffice)
    }

    override fun localFetchBoxOffice(): Flow<List<DailyBoxOffice>> {
        return boxOfficeDao.getBoxOffice()
    }

    override suspend fun delete() {
        boxOfficeDao.delete()
    }

    override fun localFetchMovieName(): Flow<List<String>> {
        return boxOfficeDao.getMovieName()
    }
}