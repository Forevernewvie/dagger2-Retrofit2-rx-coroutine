package com.example.ktrotest.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktrotest.R
import com.example.ktrotest.base.BaseActivity
import com.example.ktrotest.data.dailyBoxOffice.DailyBoxOfficeRepository
import com.example.ktrotest.data.dailyBoxOffice.local.BoxOfficeDao
import com.example.ktrotest.data.dailyBoxOffice.local.DailyBoxOfficeLocalDataSourceImpl
import com.example.ktrotest.databinding.ActivityMainBinding
import com.example.ktrotest.di.MainComponent
import com.example.ktrotest.di.MyApp
import com.example.ktrotest.di.ViewModelFactory
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.recycler.MovieAdapter
import com.example.ktrotest.room.BoxOffice
import com.example.ktrotest.viewmodel.MainViewModel
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    companion object{
        const val SQL = "SQL"
    }

    lateinit var mainComponent: MainComponent

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var date:String
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent = (application as MyApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
        mainViewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
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
        binding.month.maxValue = 12
        binding.month.minValue = 1
        binding.day.maxValue = 31
        binding.day.minValue = 1
    }

}

