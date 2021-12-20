package com.example.ktrotest.data.dailyBoxOffice

import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import kotlinx.coroutines.flow.Flow

interface DailyBoxOfficeRepository {
   fun fetchBoxOfficeData(targetDt:String):Flow<List<DailyBoxOffice>>
   suspend fun insertBoxOfficeData(boxOffice: DailyBoxOffice)
   fun requestBoxOffice() : Flow<List<DailyBoxOffice>>
   suspend fun deleteBoxOffice()
   fun requestMovieName() : Flow<List<String>>
}