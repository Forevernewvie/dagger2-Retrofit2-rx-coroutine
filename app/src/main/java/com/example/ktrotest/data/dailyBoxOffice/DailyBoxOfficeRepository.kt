package com.example.ktrotest.data.dailyBoxOffice

import androidx.lifecycle.LiveData
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface DailyBoxOfficeRepository {
   fun remoteFetchBoxOfficeData(targetDt:String): Single<OfficeResult>
   fun insertBoxOfficeData(boxOffice: List<DailyBoxOffice>): Completable
   fun localFetchBoxOffice(): Flowable<List<DailyBoxOffice>>
   fun deleteBoxOffice(): Completable
   fun localFetchMovieName(): Flowable<List<String>>
   fun deleteBelowFiveMovies(): Completable
}