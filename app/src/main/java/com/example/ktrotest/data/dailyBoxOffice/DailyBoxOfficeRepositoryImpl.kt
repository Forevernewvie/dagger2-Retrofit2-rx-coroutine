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


    override fun fetchBoxOfficeData(targetDt: String): Flow<List<DailyBoxOffice>> {
        return dailyBoxOfficeRemoteDataSource.fetchBoxOfficeData(targetDt)
    }

    override suspend fun insertBoxOfficeData(boxOffice: DailyBoxOffice) {
        dailyBoxOfficeLocalDataSource.insert(boxOffice)
    }


    override  fun requestBoxOffice(): Flow<List<DailyBoxOffice>> {
        return dailyBoxOfficeLocalDataSource.getBoxOffice()
    }

    override suspend fun deleteBoxOffice() {
        dailyBoxOfficeLocalDataSource.delete()
    }

    override  fun requestMovieName(): Flow<List<String>> {
        return dailyBoxOfficeLocalDataSource.getMovieName()
    }

}