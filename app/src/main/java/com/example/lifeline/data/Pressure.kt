package com.example.lifeline.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Pressure(
    @SerializedName("date_time")
    var dateTime: LocalDateTime,
    @SerializedName("systolic")
    var valueSystolic:Int,
    @SerializedName("diastolic")
    var valueDiastolic:Int
)
