package com.example.ktrotest.data.dailyBoxOffice.remote

import androidx.lifecycle.LiveData
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface DailyBoxOfficeRemoteDataSource {
    fun remoteFetchBoxOfficeData(targetDt:String): Single<OfficeResult>
}
