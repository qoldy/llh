package com.example.lifeline.networking.post.response

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("id")
    var id:String,
    @SerializedName("username")
    var username:String,
    @SerializedName("token")
    var token:String
)
