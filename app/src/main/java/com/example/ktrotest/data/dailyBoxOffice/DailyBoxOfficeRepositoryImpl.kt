package com.example.ktrotest.data.dailyBoxOffice

import com.example.ktrotest.OnDataListenSuccessOrFail
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSource
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSourceImpl
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSource
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import com.example.ktrotest.data.dailyBoxOffice.remote.OnBoxOfficeDataListener
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.room.BoxOffice
import javax.inject.Inject

class DailyBoxOfficeRepositoryImpl @Inject constructor(
    private val dailyBoxOfficeRemoteDataSource: DailyBoxOfficeRemoteDataSource,
    private val dailyBoxOfficeLocalDataSource: DailyBoxOfficeLocalDataSource
): DailyBoxOfficeRepository{

    override suspend fun fetchBoxOfficeData(
        targetDt: String,
        callback: OnDataListenSuccessOrFail<OfficeResult>
    ) {

        dailyBoxOfficeRemoteDataSource.fetchBoxOfficeData(targetDt,object :OnBoxOfficeDataListener{
            override fun success(offcieResult: OfficeResult) {
                callback.success(offcieResult)
            }
          }
        )
    }

    override suspend fun insertBoxOfficeData(boxOffice: BoxOffice) {
        dailyBoxOfficeLocalDataSource.insert(boxOffice)
    }


    override suspend fun requestBoxOffice(): List<BoxOffice> {
        return dailyBoxOfficeLocalDataSource.getBoxOffice()
    }

    override suspend fun deleteBoxOffice() {
        dailyBoxOfficeLocalDataSource.delete()
    }

    override suspend fun requestMovieName(): List<String> {
        return dailyBoxOfficeLocalDataSource.getMovieName()
    }

}