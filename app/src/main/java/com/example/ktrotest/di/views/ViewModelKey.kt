package com.example.ktrotest.data.dailyBoxOffice.di.views

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

//이 부분 어노테이션 꼭 볼 것!

@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class ViewModelKey(val value:KClass<out ViewModel>)