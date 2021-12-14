package com.example.ktrotest.ui

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktrotest.R
import com.example.ktrotest.data.dailyBoxOffice.BoxOfficeDataBase
import com.example.ktrotest.data.dailyBoxOffice.di.networkmodule.DaggerNetworkComponent
import com.example.ktrotest.databinding.ActivityMainBinding
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.model.OfficeResult
import com.example.ktrotest.network.HttpRequestByKtor
import com.example.ktrotest.recycler.MovieAdapter
import com.example.ktrotest.room.BoxOffice
import com.example.ktrotest.viewmodel.MainViewModel
import io.ktor.util.*
import kotlinx.coroutines.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object{
        const val SQL = "SQL"
    }
    private lateinit var binding:ActivityMainBinding
    private lateinit var date:String
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel(application)
        binding  = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.mainVM = mainViewModel
        initNumberPicker()
        initRecyclerView()




        mainViewModel.dailyBoxOfficeInfo.observe(this,{
           movieAdapter.setData(it .boxOfficeResult.dailyBoxOfficeList as ArrayList<DailyBoxOffice>)
        })

        binding.requestMovienameBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val sqlTest = mainViewModel.getMovieName()
                Log.d(SQL, "onCreate: ${sqlTest}")
            }
        }


        binding.requestBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val test = mainViewModel.getBoxOfficeInfo()
                Log.d(SQL, "onCreate: $test")
            }
        }

        binding.deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.deleteBoxOfficeInfo()
            }
       }


        binding.saveBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val test = mainViewModel.dailyBoxOfficeInfo.value?.boxOfficeResult?.dailyBoxOfficeList
                test?.forEach {
                    mainViewModel.addUser(BoxOffice(it.rank,it.movieNm,it.openDt))
                }
            }
        }

        binding.btn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                date = mainViewModel.getDateInfo()
                mainViewModel.getMovieInfo(date)
            }
       }
    }

    private fun initRecyclerView(){
        binding.movieItem.layoutManager = LinearLayoutManager(applicationContext)
        binding.movieItem.adapter =movieAdapter
        binding.movieItem.setHasFixedSize(true)
    }

    private fun initNumberPicker(){
        binding.year.maxValue = 2021
        binding.year.minValue = 2000
        binding.year.value = 1995
        binding.month.maxValue = 12
        binding.month.minValue = 1
        binding.day.maxValue = 31
        binding.day.minValue = 1
    }

}

