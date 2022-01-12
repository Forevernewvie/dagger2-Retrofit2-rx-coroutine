package com.example.ktrotest.viewmodel


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.util.Api
import com.example.ktrotest.util.DateSetter.getDate
import com.example.ktrotest.util.DateSetter.getMonth
import com.example.ktrotest.util.DateSetter.getYear
import com.example.ktrotest.util.DateSetter.nowDate
import com.example.ktrotest.util.DateSetter.yesterDay
import com.example.ktrotest.util.dateFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

//flow는 연속된 데이터를 내보내지만
//suspend fun은 연속된 값을 반환할수 없다

class MainViewModel @Inject constructor(
    private val dailyBoxOfficeRepository: DailyBoxOfficeRepository
): ViewModel() {

    //뷰모델

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
        _year.value = getYear(nowDate)
        _month.value = getMonth(nowDate)
        _day.value = getDate(nowDate)
    }

    //room movieName fetch
    fun getMovieName() = viewModelScope.launch(Dispatchers.IO) {
        dailyBoxOfficeRepository.localFetchMovieName().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    fun insertBoxOffice() = viewModelScope.launch(Dispatchers.IO) {
        _dailyBoxOfficeInfo.value?.forEach {
            dailyBoxOfficeRepository.insertBoxOfficeData(it)
        }
    }

    //room entire boxOffice fetch
    fun getBoxOfficeInfo() = viewModelScope.launch  {
        dailyBoxOfficeRepository.localFetchBoxOffice().collect {
            _fetchMsg.postValue(it.toString())
        }
    }

    //room delete
    fun deleteBoxOfficeInfo() = viewModelScope.launch(Dispatchers.IO){
        dailyBoxOfficeRepository.deleteBoxOffice()
    }

    private fun getDateInfo():String{
        return year.value.toString() + month.value?.dateFormat() + day.value?.dateFormat()
    }



    //err Handling 첫째. 데이터가 없을때 상황

    fun getDailyBoxOfficeByKtor() =viewModelScope.launch {
        _dailyBoxOfficeInfo.let {
            it.postValue(dailyBoxOfficeRepository.remoteFetchBoxOfficeData(getDateInfo()))
        }
    }
}