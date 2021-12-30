package com.example.ktrotest.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.util.*

object DateSetter {
    /* Api level 26부터라 쓰기엔 애매함. wemeet (api level 23~30)
    @JvmStatic
    private val nowDate: LocalDate = LocalDate.now()

    fun getDate(): Int = nowDate.dayOfMonth
    fun getYear(): Int = nowDate.year
    fun getMonth(): Int = nowDate.monthValue
    */
    val nowDate = Date(System.currentTimeMillis())

    val yesterDay = Date(System.currentTimeMillis()-86400000)


    private val year = SimpleDateFormat("yyyy")
    private val month = SimpleDateFormat("MM")
    private val date = SimpleDateFormat("dd")

    fun <T>getYear(time:T): Int =  year.format(time).toInt()
    fun <T>getMonth(time:T): Int = month.format(time).toInt()
    fun <T>getDate(time: T): Int = date.format(time).toInt()

    //하루 전 날짜 -> 해당 당일 박스오피스 정보는 없음..




}
