package com.example.lifeline.networking.post.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("username")
    var login:String,
    @SerializedName("password")
    var password:String
)
