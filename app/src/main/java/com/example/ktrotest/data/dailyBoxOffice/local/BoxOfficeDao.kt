package com.example.ktrotest.data.dailyBoxOffice.local

import androidx.room.*
import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.Flow

@Dao
interface BoxOfficeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(boxOffice: DailyBoxOffice)

     @Query("Delete from boxOffice")
     fun delete()

     @Query("Select movieNm From boxOffice")
     fun getMovieName() : Flow<List<String>>

    @Query("Select * From boxOffice")
     fun getBoxOffice() : Flow<List<DailyBoxOffice>>
}