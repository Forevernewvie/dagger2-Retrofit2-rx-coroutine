package com.example.ktrotest.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker

class JaebinNumberPicker: NumberPicker{

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        processXmlAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        processXmlAttributes(attrs)
    }

    private fun processXmlAttributes(attrs: AttributeSet) {
        this.minValue = attrs.getAttributeIntValue(null,"min",0)
        this.maxValue = attrs.getAttributeIntValue(null,"max",0)
    }

}