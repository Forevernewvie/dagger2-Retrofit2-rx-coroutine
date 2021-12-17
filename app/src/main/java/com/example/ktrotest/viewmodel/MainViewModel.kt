package com.example.ktrotest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.ktrotest.OnDataListenSuccessOrFail
import com.example.ktrotest.data.dailyBoxOffice.BoxOfficeDataBase
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepositoryImpl
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSourceImpl
import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.room.BoxOffice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val dailyBoxOfficeRepository: DailyBoxOfficeRepository
    ): ViewModel() {

    private val _dailyBoxOfficeInfo = MutableLiveData<OfficeResult>()

    val dailyBoxOfficeInfo: LiveData<OfficeResult> = _dailyBoxOfficeInfo
    //양방향 데이터 바인딩, 접근 제한자 있으면 안됨 구글에서 원래 그렇게 해놧나?
    val _year = MutableLiveData<Int>()
    val year:LiveData<Int>
        get() = _year

    val _month = MutableLiveData<Int>()
    val month:LiveData<Int>
        get() = _month

    val _day = MutableLiveData<Int>()
    val day:LiveData<Int>
        get() = _day


    init {
        _year.value = 2021
        _month.value = 12
        _day.value = 1
    }

    suspend fun getMovieName() : List<String> {
        return dailyBoxOfficeRepository.requestMovieName()
    }

     suspend fun addUser(boxOffice:BoxOffice){
         dailyBoxOfficeRepository.insertBoxOfficeData(boxOffice)
    }

     suspend fun getBoxOfficeInfo() :List<BoxOffice>  {
         return dailyBoxOfficeRepository.requestBoxOffice()
    }

    suspend fun deleteBoxOfficeInfo(){
        dailyBoxOfficeRepository.deleteBoxOffice()
    }

    private fun getMonthAndDay():String{
        var temp_Month = month.value.toString()
        var temp_Day = day.value.toString()
        var result = ""
        if(temp_Month.length<2){
            temp_Month = "0" + month.value.toString()
        }
        if ( temp_Day.length<2){
            temp_Day = "0" + day.value.toString()
        }
        result = temp_Month+temp_Day
        return result
    }


    fun getDateInfo():String{
        return year.value.toString()+getMonthAndDay()
    }

    suspend fun getMovieInfo(date:String) {
        dailyBoxOfficeRepository.fetchBoxOfficeData(date,object:OnDataListenSuccessOrFail<OfficeResult>{
            override fun success(item: OfficeResult) {
                _dailyBoxOfficeInfo.postValue(item)
            }
        })
    }
}