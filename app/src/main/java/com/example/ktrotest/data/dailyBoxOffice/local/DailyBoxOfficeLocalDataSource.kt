package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow

interface DailyBoxOfficeLocalDataSource {
     suspend fun insert(boxOffice: DailyBoxOffice)
     fun localFetchBoxOffice() : Flow<List<DailyBoxOffice>>
     suspend fun delete()
     fun localFetchMovieName() : Flow<List<String>>
}