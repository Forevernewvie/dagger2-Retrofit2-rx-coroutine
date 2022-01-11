package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DailyBoxOfficeLocalDataSource {
     fun insert(boxOffice: List<DailyBoxOffice>): Completable
     fun localFetchBoxOffice(): Flowable<List<DailyBoxOffice>>
     fun delete(): Completable
     fun localFetchMovieName(): Flowable<List<String>>
     fun deleteBelowFiveMovies(): Completable
}