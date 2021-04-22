package com.example.lifeline.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Temperature(
    @SerializedName("date_time")
    var dateTime: LocalDateTime,
    @SerializedName("temperature")
    var value:Double
)
