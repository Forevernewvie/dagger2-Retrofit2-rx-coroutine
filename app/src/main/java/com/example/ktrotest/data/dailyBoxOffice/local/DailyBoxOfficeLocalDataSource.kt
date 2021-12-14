package com.example.ktrotest.data.dailyBoxOffice.local

import com.example.ktrotest.room.BoxOffice

interface DailyBoxOfficeLocalDataSource {
     suspend fun insert(boxOffice: BoxOffice)
     suspend fun getBoxOffice() : List<BoxOffice>
     suspend fun delete()
     suspend fun getMovieName() : List<String>
}