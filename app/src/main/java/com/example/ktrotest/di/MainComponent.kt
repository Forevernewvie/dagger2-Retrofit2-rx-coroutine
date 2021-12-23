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

/*
SubComponent 부모 Component를 갖고 있는 Component
SubComponent에서 Builder나 Factory 어노테이션 붙어줘야 부모 Component에서 코드가 생성됨 /없으면 생성 안됨

 */