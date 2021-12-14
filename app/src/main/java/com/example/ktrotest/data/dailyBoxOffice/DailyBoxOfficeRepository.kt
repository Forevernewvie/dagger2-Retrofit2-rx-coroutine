package com.example.ktrotest.data.dailyBoxOffice

import com.example.ktrotest.OnDataListenSuccessOrFail
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.room.BoxOffice

interface DailyBoxOfficeRepository {
   suspend fun fetchBoxOfficeData(targetDt:String,callback:OnDataListenSuccessOrFail<OfficeResult>)
   suspend fun insertBoxOfficeData(boxOffice: BoxOffice)
   suspend fun requestBoxOffice() : List<BoxOffice>
   suspend fun deleteBoxOffice()
   suspend fun requestMovieName() : List<String>
}