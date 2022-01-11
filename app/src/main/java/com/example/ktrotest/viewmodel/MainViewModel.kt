package com.example.ktrotest.viewmodel


import androidx.lifecycle.*
import com.example.ktrotest.base.BaseViewModel
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.util.DateSetter.getDate
import com.example.ktrotest.util.DateSetter.getMonth
import com.example.ktrotest.util.DateSetter.getYear
import com.example.ktrotest.util.DateSetter.nowDate
import com.example.ktrotest.util.dateFormat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

//flow는 연속된 데이터를 내보내지만(지속적으로 뷰가 바뀌어야 하는 항목에 flow ex) 좋아요, 북마크 수)
//suspend fun은 연속된 값을 반환할 수 없다(한번 출력되고 재호출 전까지 바뀔일 없는것들)

//Rxjava -> ???

class MainViewModel @Inject constructor(
    private val dailyBoxOfficeRepository: DailyBoxOfficeRepository
): BaseViewModel() {

    private val _dailyBoxOfficeInfo = MutableLiveData<List<DailyBoxOffice>>()
    val dailyBoxOfficeInfo: LiveData<List<DailyBoxOffice>> = _dailyBoxOfficeInfo

    private val _fetchMsg = MutableLiveData<String>()
    val fetchMsg:LiveData<String>
        get() = _fetchMsg

    private val _fetchBoxOffice = MutableLiveData<List<DailyBoxOffice>>()
    val fetchBoxOffice:LiveData<List<DailyBoxOffice>>
        get() = _fetchBoxOffice

    //양방향 데이터 바인딩, 접근 제한자 있으면 동작안됨
    var year = MutableLiveData<Int>()
    var month = MutableLiveData<Int>()
    var day = MutableLiveData<Int>()


    init {
        year.value = getYear(nowDate)
        month.value = getMonth(nowDate)
        day.value = getDate(nowDate)
    }

    private fun getDateInfo():String{
        return year.value.toString() + month.value?.dateFormat() + day.value?.dateFormat()
    }

    //room movieName fetch
    fun getMovieName(){
        compositeDisposable.add(
            dailyBoxOfficeRepository.localFetchMovieName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movie ->
                    _fetchMsg.postValue(movie.toString())
                }
        )
    }

    fun insertBoxOffice() {
        compositeDisposable.add(
            _dailyBoxOfficeInfo.value.let {
                dailyBoxOfficeRepository.insertBoxOfficeData(it!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        )

    }

    //room entire boxOffice fetch
    fun getBoxOfficeInfo() {
        compositeDisposable.add(
            dailyBoxOfficeRepository.localFetchBoxOffice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ movieData ->
                    _fetchMsg.postValue(movieData.toString())
                }
        )
    }

    //room delete
    fun deleteBoxOfficeInfo() {
        compositeDisposable.add(
            dailyBoxOfficeRepository.deleteBoxOffice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }




    //err Handling 첫째. 데이터가 없을때 상황
    fun getDailyBoxOfficeByKtor()  {
        compositeDisposable.add(
            dailyBoxOfficeRepository.remoteFetchBoxOfficeData(getDateInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ data->
                    _dailyBoxOfficeInfo.postValue(data.boxOfficeResult.dailyBoxOfficeList)
                }
        )

    }
}