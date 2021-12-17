package com.example.ktrotest.di

import android.content.Context
import com.example.ktrotest.di.data.LocalModule
import com.example.ktrotest.di.data.RemoteModule
import com.example.ktrotest.di.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,RepositoryModule::class ,LocalModule::class,ViewModelModule::class,ViewModelFactoryModule::class,SubComponentModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

    fun mainComponent() : MainComponent.Factory

}