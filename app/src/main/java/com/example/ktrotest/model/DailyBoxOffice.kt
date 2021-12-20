package com.example.ktrotest.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "boxOffice")
data class DailyBoxOffice(
    @PrimaryKey val rank: String,
    @ColumnInfo(name ="movieNm") val movieNm: String,
    @ColumnInfo(name ="openDt") val openDt: String
)
