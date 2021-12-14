package com.example.ktrotest.data.dailyBoxOffice

import com.example.ktrotest.OnDataListenSuccessOrFail
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSourceImpl
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import com.example.ktrotest.data.dailyBoxOffice.remote.OnBoxOfficeDataListener
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.room.BoxOffice

class DailyBoxOfficeRepositoryImpl(
    private val dailyBoxOfficeRemoteDataSourceImpl: DailyBoxOfficeRemoteDataSourceImpl,
    private val dailyBoxOfficeLocalDataSourceImpl: DailyBoxOfficeLocalDataSourceImpl
): DailyBoxOfficeRepository{

    override suspend fun fetchBoxOfficeData(
        targetDt: String,
        callback: OnDataListenSuccessOrFail<OfficeResult>
    ) {
        dailyBoxOfficeRemoteDataSourceImpl.fetchBoxOfficeData(targetDt,object :OnBoxOfficeDataListener{
            override fun success(offcieResult: OfficeResult) {
                callback.success(offcieResult)
            }
          }
        )
    }

    override suspend fun insertBoxOfficeData(boxOffice: BoxOffice) {
        dailyBoxOfficeLocalDataSourceImpl.insert(boxOffice)
    }


    override suspend fun requestBoxOffice(): List<BoxOffice> {
        return dailyBoxOfficeLocalDataSourceImpl.getBoxOffice()
    }

    override suspend fun deleteBoxOffice() {
        dailyBoxOfficeLocalDataSourceImpl.delete()
    }

    override suspend fun requestMovieName(): List<String> {
        return dailyBoxOfficeLocalDataSourceImpl.getMovieName()
    }

}