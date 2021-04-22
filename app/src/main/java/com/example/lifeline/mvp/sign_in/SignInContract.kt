package com.example.lifeline.mvp.sign_in

interface SignInContract {
    interface View{
        fun showError()
        fun onSuccess()
        fun getLogin():String
        fun getPassword():String
    }
    interface Presenter{
        fun signIn()
    }
}