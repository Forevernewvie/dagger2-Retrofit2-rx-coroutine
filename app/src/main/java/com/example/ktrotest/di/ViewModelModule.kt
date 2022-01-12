package com.example.ktrotest.di

import androidx.lifecycle.ViewModel
import com.example.ktrotest.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModels(mainViewModel: MainViewModel) : ViewModel
}