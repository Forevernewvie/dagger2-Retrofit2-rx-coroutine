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
    @JvmStatic
    private val nowDate = Date(System.currentTimeMillis())

    private val year = SimpleDateFormat("yyyy")
    private val month = SimpleDateFormat("MM")
    private val date = SimpleDateFormat("dd")

    fun getYear():Int = year.format(nowDate).toInt()
    fun getMonth():Int = month.format(nowDate).toInt()
    fun getDate():Int = date.format(nowDate).toInt()


}
