package com.example.lifeline.mvp.sign_up

interface SignUpContract {
    interface View{
        fun showError()
        fun onSuccess()
        fun getEmail():String
        fun getLogin():String
        fun getPassword():String
        fun getConfirm():String
    }

    interface Presenter{
        fun signUp()
    }
}