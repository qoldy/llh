package com.example.lifeline.data

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        var id:Long,
        @SerializedName("username")
        var login:String,
//        @SerializedName("email")
//        var email:String
)