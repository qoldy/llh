package com.example.lifeline.networking.post.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("username")
    var username:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String
)