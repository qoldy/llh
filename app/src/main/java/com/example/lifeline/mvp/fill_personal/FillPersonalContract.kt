package com.example.lifeline.mvp.fill_personal

interface FillPersonalContract {
    interface View{
        fun showError()
        fun onSuccess()
        fun getFullName():String
        fun getAddress():String?
        fun getPolicy():String?
    }

    interface Presenter{
    }
}