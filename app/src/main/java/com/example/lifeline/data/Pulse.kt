package com.example.lifeline.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Pulse(
        @SerializedName("user_id")
        var userId:Long,
        @SerializedName("date_time")
        var dateTime:LocalDateTime,
        @SerializedName("pulse")
        var value:Int
)