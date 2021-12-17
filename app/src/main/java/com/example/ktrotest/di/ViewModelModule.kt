package com.example.ktrotest.di

import androidx.lifecycle.ViewModel
import com.example.ktrotest.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModels(mainViewModel: MainViewModel) : ViewModel
}