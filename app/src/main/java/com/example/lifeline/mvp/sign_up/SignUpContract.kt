package com.example.lifeline.mvp.sign_up

import android.content.Context

interface SignUpContract {
    interface View{
        fun showError(error:String)
        fun onSuccess()
        fun getEmail():String
        fun getLogin():String
        fun getPassword():String
        fun getConfirm():String
        fun getContext(): Context
    }

    interface Presenter{
        fun signUp()
    }
}