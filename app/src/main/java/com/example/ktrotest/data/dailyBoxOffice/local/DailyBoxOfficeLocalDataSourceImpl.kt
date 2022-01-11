package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class DailyBoxOfficeLocalDataSourceImpl @Inject constructor (
    private val boxOfficeDao: BoxOfficeDao
):DailyBoxOfficeLocalDataSource {

    override fun insert(boxOffice: List<DailyBoxOffice>): Completable =
        boxOfficeDao.insert(boxOffice)

    override fun localFetchBoxOffice(): Flowable<List<DailyBoxOffice>> =
         boxOfficeDao.getBoxOffice()

    override fun delete(): Completable =
        boxOfficeDao.delete()

    override fun localFetchMovieName(): Flowable<List<String>> =
         boxOfficeDao.getMovieName()

    override fun deleteBelowFiveMovies(): Completable =
        boxOfficeDao.deleteBelowFiveMovies()

}