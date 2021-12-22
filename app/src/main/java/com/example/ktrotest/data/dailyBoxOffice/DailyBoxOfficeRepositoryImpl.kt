package com.example.ktrotest.data.dailyBoxOffice


import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyBoxOfficeRepositoryImpl @Inject constructor(
    private val dailyBoxOfficeRemoteDataSource: DailyBoxOfficeRemoteDataSource,
    private val dailyBoxOfficeLocalDataSource: DailyBoxOfficeLocalDataSource
): DailyBoxOfficeRepository{

    override suspend fun remoteFetchBoxOfficeData(targetDt: String): List<DailyBoxOffice> {
        return dailyBoxOfficeRemoteDataSource.remoteFetchBoxOfficeData(targetDt)
    }

    override suspend fun insertBoxOfficeData(boxOffice: DailyBoxOffice) {
        dailyBoxOfficeLocalDataSource.insert(boxOffice)
    }

    override fun localFetchBoxOffice(): Flow<List<DailyBoxOffice>> {
        return dailyBoxOfficeLocalDataSource.localFetchBoxOffice()
    }

    override suspend fun deleteBoxOffice() {
        dailyBoxOfficeLocalDataSource.delete()
    }

    override fun localFetchMovieName(): Flow<List<String>> {
        return dailyBoxOfficeLocalDataSource.localFetchMovieName()
    }

}