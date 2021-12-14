package com.example.ktrotest

interface OnDataListenSuccessOrFail<T> {
    fun success(item:T)
}