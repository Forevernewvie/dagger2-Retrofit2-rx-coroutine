package com.example.ktrotest.data.dailyBoxOffice.di.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

// 뷰모델 팩토리 패턴을 왜 쓰는지
// 제네릭 out in 공변 반공변 확실히 이해하기
// 함수 자체 이해하기

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModelFactory>, @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val found = viewModelMap.entries.find { modelClass.isAssignableFrom(it.key) }
        val creator = found?.value
            ?:throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        try{
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}