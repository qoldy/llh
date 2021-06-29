package com.example.lifeline.mvp.sign_in

import android.content.Context

interface SignInContract {
    interface View{
        fun showError(error:String)
        fun onSuccess()
        fun getLogin():String
        fun getPassword():String
        fun getContext():Context
    }
    interface Presenter{
        fun signIn()
    }
}