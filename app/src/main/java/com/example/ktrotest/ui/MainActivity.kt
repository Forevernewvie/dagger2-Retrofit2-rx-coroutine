package com.example.ktrotest.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktrotest.R
import com.example.ktrotest.base.BaseActivity
import com.example.ktrotest.databinding.ActivityMainBinding
import com.example.ktrotest.di.MainComponent
import com.example.ktrotest.di.MyApp
import com.example.ktrotest.model.DailyBoxOffice
import com.example.ktrotest.recycler.MovieAdapter
import com.example.ktrotest.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainComponent: MainComponent
    private lateinit var mainViewModel: MainViewModel
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
            movieAdapter.setData(it as ArrayList<DailyBoxOffice>)
        })

        binding.requestMovienameBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.getMovieName().collect {
                    binding.testString.text = it.toString()
                }
            }
        }

        binding.requestBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.getBoxOfficeInfo().collect {
                    binding.testString.text = it.toString()
                }
            }
        }

        binding.deleteBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.deleteBoxOfficeInfo()
            }
        }

        binding.saveBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.dailyBoxOfficeInfo.value?.forEach {
                    mainViewModel.addUser(it)
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

