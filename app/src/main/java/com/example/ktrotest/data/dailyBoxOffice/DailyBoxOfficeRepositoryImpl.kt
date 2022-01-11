package com.example.ktrotest.data.dailyBoxOffice


import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyBoxOfficeRepositoryImpl @Inject constructor(
    private val dailyBoxOfficeRemoteDataSource: DailyBoxOfficeRemoteDataSource,
    private val dailyBoxOfficeLocalDataSource: DailyBoxOfficeLocalDataSource
): DailyBoxOfficeRepository{

    override fun remoteFetchBoxOfficeData(targetDt: String): Single<OfficeResult> {
        return dailyBoxOfficeRemoteDataSource.remoteFetchBoxOfficeData(targetDt)
    }

    override fun insertBoxOfficeData(boxOffice: List<DailyBoxOffice>): Completable =
        dailyBoxOfficeLocalDataSource.insert(boxOffice)


    override fun localFetchBoxOffice(): Flowable<List<DailyBoxOffice>> {
        return dailyBoxOfficeLocalDataSource.localFetchBoxOffice()
    }

    override fun deleteBoxOffice(): Completable =
        dailyBoxOfficeLocalDataSource.delete()


    override fun localFetchMovieName(): Flowable<List<String>> {
        return dailyBoxOfficeLocalDataSource.localFetchMovieName()
    }

    override fun deleteBelowFiveMovies(): Completable =
        dailyBoxOfficeLocalDataSource.deleteBelowFiveMovies()

}