package com.example.ktrotest.ui

import android.os.Bundle
import android.util.Log
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
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainComponent: MainComponent
    private lateinit var mainViewModel: MainViewModel
    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent = (application as MyApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding.mainVM = mainViewModel
        initRecyclerView()

        mainViewModel.dailyBoxOfficeInfo.observe(this,{
            movieAdapter.setData(it as ArrayList<DailyBoxOffice>)
        })

    }


    private fun initRecyclerView(){
        binding.movieItem.layoutManager = LinearLayoutManager(applicationContext)
        binding.movieItem.adapter =movieAdapter
        binding.movieItem.setHasFixedSize(true)
    }

}

