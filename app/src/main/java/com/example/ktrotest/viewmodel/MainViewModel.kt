package com.example.ktrotest.viewmodel

import androidx.lifecycle.*
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val dailyBoxOfficeRepository: DailyBoxOfficeRepository
    ): ViewModel() {

    private val _dailyBoxOfficeInfo = MutableLiveData<List<DailyBoxOffice>>()
    val dailyBoxOfficeInfo: LiveData<List<DailyBoxOffice>> = _dailyBoxOfficeInfo

    //양방향 데이터 바인딩, 접근 제한자 있으면 동작안됨
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
        viewModelScope.launch {
            _year.value = 2021
            _month.value = 12
            _day.value = 1
        }
    }

     fun getMovieName() : Flow<List<String>> {
        return dailyBoxOfficeRepository.requestMovieName()
    }

     suspend fun addUser(boxOffice: DailyBoxOffice){
         dailyBoxOfficeRepository.insertBoxOfficeData(boxOffice)
    }

     fun getBoxOfficeInfo() :Flow<List<DailyBoxOffice>>  {
         return dailyBoxOfficeRepository.requestBoxOffice()
    }

    suspend fun deleteBoxOfficeInfo(){
        dailyBoxOfficeRepository.deleteBoxOffice()
    }

    private fun getMonthAndDay():String{
        var tempMonth = month.value.toString()
        var tempDay = day.value.toString()

        if(tempMonth.length<2){
            tempMonth = "0" + month.value.toString()
        }
        if ( tempDay.length<2){
            tempDay = "0" + day.value.toString()
        }
        return  tempMonth+tempDay
    }


    fun getDateInfo():String{
        return year.value.toString()+getMonthAndDay()
    }

    suspend fun getMovieInfo(date:String) {
        dailyBoxOfficeRepository.fetchBoxOfficeData(date).collect {
            _dailyBoxOfficeInfo.postValue(it)
            //데이터 수집(소비)
        }
    }
}