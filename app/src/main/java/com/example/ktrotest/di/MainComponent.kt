package com.example.ktrotest.di

import com.example.ktrotest.ui.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : MainComponent
    }

    fun inject(mainActivity: MainActivity)
}