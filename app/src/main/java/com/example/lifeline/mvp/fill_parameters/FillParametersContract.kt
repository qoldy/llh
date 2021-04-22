package com.example.lifeline.mvp.fill_parameters

import java.time.LocalDate

interface FillParametersContract {
    interface View{
        fun showError()
        fun onSuccess()
        fun getDateOfBirth():LocalDate
        fun getGender():String
        fun getHeight():String
        fun getWeight():String
    }

    interface Presenter{
    }
}