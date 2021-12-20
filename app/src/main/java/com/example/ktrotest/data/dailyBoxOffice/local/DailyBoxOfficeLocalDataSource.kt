package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow


// flow 타입은 emit하지 않고도 collect가 되는지 궁금합니다.


interface DailyBoxOfficeLocalDataSource {
     suspend fun insert(boxOffice: DailyBoxOffice)
     fun getBoxOffice() : Flow<List<DailyBoxOffice>>
     suspend fun delete()
     fun getMovieName() : Flow<List<String>>
}