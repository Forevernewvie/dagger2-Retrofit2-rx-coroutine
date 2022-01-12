package com.example.ktrotest.model

import android.widget.Toast
import androidx.annotation.StringRes

data class ToastMessage (
    @StringRes val msgResId: Int? = null,
    val msg: String? = null,
    val duration: Int = Toast.LENGTH_SHORT
)