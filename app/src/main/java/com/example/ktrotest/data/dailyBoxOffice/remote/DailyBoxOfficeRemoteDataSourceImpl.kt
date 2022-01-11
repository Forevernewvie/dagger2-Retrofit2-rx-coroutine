package com.example.ktrotest.data.dailyBoxOffice.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ktrotest.util.Api
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.MovieApi
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.util.fetchBoxOfficeData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class DailyBoxOfficeRemoteDataSourceImpl @Inject constructor
    (private val movieApi: MovieApi) :
    DailyBoxOfficeRemoteDataSource {

    override fun remoteFetchBoxOfficeData(targetDt: String): Single<OfficeResult> {
        return movieApi.fetchBoxOfficeMovieList(Api.KEY,targetDt)
    }
}
