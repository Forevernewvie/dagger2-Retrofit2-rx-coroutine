package com.example.ktrotest.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

// 위밋 앱 코드 참조 작성

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes val layoutId: Int
) :AppCompatActivity() {

    lateinit var binding: B
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutId)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}