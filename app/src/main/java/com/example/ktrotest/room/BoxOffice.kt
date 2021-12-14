package com.example.ktrotest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boxOffice")
data class BoxOffice(
    @PrimaryKey val rank: String,
    @ColumnInfo(name ="movieNm") val movieNm: String,
    @ColumnInfo(name ="openDt") val openDt: String
)
