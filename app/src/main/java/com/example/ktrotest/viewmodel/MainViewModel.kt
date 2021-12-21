package com.example.ktrotest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val dailyBoxOfficeRepository: DailyBoxOfficeRepository
): ViewModel() {

    private val _dailyBoxOfficeInfo = MutableLiveData<List<DailyBoxOffice>>()
    val dailyBoxOfficeInfo: LiveData<List<DailyBoxOffice>> = _dailyBoxOfficeInfo

    private val _fetchMsg = MutableLiveData<String>()
    val fetchMsg:LiveData<String>
        get() = _fetchMsg

    //양방향 데이터 바인딩, 접근 제한자 있으면 동작안됨
    val _year = MutableLiveData<Int>()
    val year:LiveData<Int>
        get() = _year

    val _month = MutableLiveData<Int>()
    val month:LiveData<Int>
        get() = _month

    val _day = MutableLiveData<Int>()
    val day: LiveData<Int>
        get() = _day


    init {
        viewModelScope.launch {
            _year.value = 2021
            _month.value = 12
            _day.value = 1
        }
    }

    suspend fun getMovieName() {
        dailyBoxOfficeRepository.fetchMovieName().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    suspend fun addUser(boxOffice: DailyBoxOffice){
        dailyBoxOfficeRepository.insertBoxOfficeData(boxOffice)
    }

    suspend fun getBoxOfficeInfo()   {
        dailyBoxOfficeRepository.fetchBoxOffice().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    suspend fun deleteBoxOfficeInfo(){
        dailyBoxOfficeRepository.deleteBoxOffice()
    }

    private fun getMonthAndDay():String{
        var movieMonth = month.value.toString()
        var movieDay = day.value.toString()
        movieMonth.let{
            if(movieMonth.length<2){ movieMonth.map { movieMonth = "0$movieMonth"}}
        }
        movieDay.let {
            if(movieDay.length<2){  movieDay.map { movieDay = "0$movieDay"}}
        }
        return movieMonth+movieDay
    }


    fun getDateInfo():String{
        return year.value.toString()+getMonthAndDay()
    }

    suspend fun getDailyBoxOfficeInfo(date:String) {
        dailyBoxOfficeRepository.fetchBoxOfficeData(date).collect {
            _dailyBoxOfficeInfo.postValue(it)   //데이터 수집(소비)
        }
    }
}