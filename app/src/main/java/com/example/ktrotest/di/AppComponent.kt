package com.example.ktrotest.di.views

import android.content.Context
import com.example.ktrotest.di.data.ApiModule
import com.example.ktrotest.di.data.LocalDataModule
import com.example.ktrotest.di.data.RemoteDataModule
import com.example.ktrotest.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class, ViewModelFactoryModule::class, ViewModelModule::class, LocalDataModule::class, RemoteDataModule::class, RepositoryModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

}