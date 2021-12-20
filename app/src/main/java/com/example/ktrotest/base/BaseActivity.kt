package com.example.ktrotest.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

// 위밋 앱 코드 참조 작성

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes val layoutId: Int
) :AppCompatActivity() {

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutId)
        binding.lifecycleOwner = this
    }

    protected fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}