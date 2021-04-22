package com.example.lifeline.data

import com.google.gson.annotations.SerializedName

data class Personal(
    @SerializedName("full_name")
    var fullName:String,
    @SerializedName("address")
    var address:String?,
    @SerializedName("policy")
    var policy:String?
)