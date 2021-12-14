package com.example.ktrotest.data.dailyBoxOffice.local

import androidx.room.*
import com.example.ktrotest.room.BoxOffice

@Dao
interface BoxOfficeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(boxOffice: BoxOffice)

     @Query("Delete from boxOffice")
     fun delete()

     @Query("Select movieNm From boxOffice")
     fun getMovieName() : List<String>

    @Query("Select * From boxOffice")
     fun getBoxOffice() : List<BoxOffice>
}