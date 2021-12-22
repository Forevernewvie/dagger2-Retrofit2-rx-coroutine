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
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.mainVM = mainViewModel

        initRecyclerView()

        mainViewModel.dailyBoxOfficeInfo.observe(this, {
            movieAdapter.setData(it as ArrayList<DailyBoxOffice>)
        })

        binding.requestMovienameBtn.setOnClickListener {
                mainViewModel.getMovieName()
        }

            binding.requestBtn.setOnClickListener {
                mainViewModel.getBoxOfficeInfo()
            }

            binding.deleteBtn.setOnClickListener {
                mainViewModel.deleteBoxOfficeInfo()
            }

            binding.saveBtn.setOnClickListener {
               mainViewModel.dailyBoxOfficeInfo.value?.forEach {
                    mainViewModel.insertBoxOffice(it)
                }
            }

            binding.btn.setOnClickListener {
                    date = mainViewModel.getDateInfo()
                    mainViewModel.getDailyBoxOfficeInfo(date)
            }
        }


    private fun initRecyclerView(){
        binding.movieItem.layoutManager = LinearLayoutManager(applicationContext)
        binding.movieItem.adapter =movieAdapter
        binding.movieItem.setHasFixedSize(true)
    }

}

