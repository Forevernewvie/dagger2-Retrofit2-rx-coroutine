package com.example.ktrotest.data.dailyBoxOffice.local

import androidx.room.*
import com.example.ktrotest.model.DailyBoxOffice
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface BoxOfficeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(boxOffice: List<DailyBoxOffice>): Completable

     @Query("Delete from boxOffice")
     fun delete(): Completable

     @Query("Select movieNm From boxOffice")
     fun getMovieName() : Flowable<List<String>>

    @Query("Select * From boxOffice")
     fun getBoxOffice() : Flowable<List<DailyBoxOffice>>

     @Query( "Delete From boxOffice where rank >=5")
     fun deleteBelowFiveMovies(): Completable
}