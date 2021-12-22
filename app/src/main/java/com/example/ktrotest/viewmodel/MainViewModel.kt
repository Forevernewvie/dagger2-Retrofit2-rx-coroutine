package com.example.ktrotest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.model.DailyBoxOffice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        _year.value = 2021
        _month.value = 12
        _day.value = 1
    }

    //room movieName fetch
    fun getMovieName() =viewModelScope.launch(Dispatchers.IO) {
        dailyBoxOfficeRepository.localFetchMovieName().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    fun insertBoxOffice(boxOffice: DailyBoxOffice) = viewModelScope.launch(Dispatchers.IO) {
            dailyBoxOfficeRepository.insertBoxOfficeData(boxOffice)
    }

    //room entire boxOffice fetch
    fun getBoxOfficeInfo() =viewModelScope.launch  {
        dailyBoxOfficeRepository.localFetchBoxOffice().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    //room delete
    fun deleteBoxOfficeInfo() = viewModelScope.launch(Dispatchers.IO){
            dailyBoxOfficeRepository.deleteBoxOffice()
    }

    private fun getMonthAndDay():String{
        var movieMonth = month.value.toString()
        var movieDay = day.value.toString()

        if(movieMonth.length<2){
            movieMonth = "0" + month.value.toString()
        }
        if ( movieDay.length<2){
            movieDay = "0" + day.value.toString()
        }
        return movieMonth+movieDay
    }


    fun getDateInfo():String{
        return year.value.toString()+getMonthAndDay()
    }

    fun getDailyBoxOfficeInfo(date:String) =viewModelScope.launch(Dispatchers.IO) {
        _dailyBoxOfficeInfo.postValue(dailyBoxOfficeRepository.remoteFetchBoxOfficeData(date))
    }
}