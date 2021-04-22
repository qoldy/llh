package com.example.lifeline.utils

import android.annotation.SuppressLint
import android.content.Context

abstract class LifeLine{
    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun setContext(context: Context){
            this.context=context
        }

        fun getContext():Context{
            return context
        }
    }
}