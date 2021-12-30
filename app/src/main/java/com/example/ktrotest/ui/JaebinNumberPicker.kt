package com.example.ktrotest.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import com.example.ktrotest.R

//JvmOverloads 사용시 생성자에 파라미터가 N개  default 매개변수를 M개
// N-m개의 경우의 생성자를 오버로딩 해줌 -> 주의
class JaebinNumberPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?=null
) :NumberPicker(context,attrs){


    init {
        if (attrs != null) {
            processXmlAttributes(attrs)
        }
    }

    private fun processXmlAttributes(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.JaebinNumberPicker)

        try {
            this.minValue = attributes.getInt(R.styleable.JaebinNumberPicker_min, 0)
            this.maxValue = attributes.getInt(R.styleable.JaebinNumberPicker_max, 0)
        } finally {
            attributes.recycle()
        }
    }

}