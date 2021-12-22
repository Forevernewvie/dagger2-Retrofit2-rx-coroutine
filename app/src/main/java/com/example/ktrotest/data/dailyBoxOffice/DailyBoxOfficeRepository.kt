package com.example.ktrotest.data.dailyBoxOffice

import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import kotlinx.coroutines.flow.Flow

interface DailyBoxOfficeRepository {
   suspend fun remoteFetchBoxOfficeData(targetDt:String):List<DailyBoxOffice>
   suspend fun insertBoxOfficeData(boxOffice: DailyBoxOffice)
   fun localFetchBoxOffice() : Flow<List<DailyBoxOffice>>
   suspend fun deleteBoxOffice()
   fun localFetchMovieName() : Flow<List<String>>
}