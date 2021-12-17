package com.example.ktrotest.data.dailyBoxOffice.remote

import com.example.ktrotest.model.OfficeResult

interface DailyBoxOfficeRemoteDataSource {
    suspend fun fetchBoxOfficeData(targetDt:String, callback: OnBoxOfficeDataListener)
}

interface OnBoxOfficeDataListener{
    fun success(officeResult: OfficeResult)
}