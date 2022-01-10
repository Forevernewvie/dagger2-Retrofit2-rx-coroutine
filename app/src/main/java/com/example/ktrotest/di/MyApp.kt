package com.example.ktrotest.di


import android.app.Application
import dagger.android.DaggerApplication

class MyApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}