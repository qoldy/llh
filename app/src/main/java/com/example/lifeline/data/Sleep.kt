package com.example.lifeline.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.LocalTime

data class Sleep(
    @SerializedName("date_time")
    var dateTime: LocalDateTime,
    @SerializedName("sleep_time")
    var value:LocalTime
    )
