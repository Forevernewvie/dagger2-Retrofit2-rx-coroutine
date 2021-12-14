package com.example.ktrotest.di.views

import android.app.Application
import com.example.ktrotest.di.AppComponent


//왜 쓰지 이건? 액티비티일때 쉽게 주입하려고?
class MyApplication :Application() {
    val appComponent: AppComponent by lazy {
        //컴포넌트 초기화
        DaggerAppComponent.factory().create(this)
    }
}