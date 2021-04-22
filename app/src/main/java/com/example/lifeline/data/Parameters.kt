package com.example.lifeline.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Parameters(
    @SerializedName("birth_date")
    var dateOfBirth:LocalDate,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("height")
    var height:Int,
    @SerializedName("weight")
    var weight:Double
)
