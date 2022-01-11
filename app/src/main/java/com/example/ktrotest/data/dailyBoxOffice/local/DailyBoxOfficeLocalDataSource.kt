package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface DailyBoxOfficeLocalDataSource {
     fun insert(boxOffice: List<DailyBoxOffice>): Completable
     fun localFetchBoxOffice() : Single<List<DailyBoxOffice>>
     fun delete(): Completable
     fun localFetchMovieName() : Single<List<String>>
}