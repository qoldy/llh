package com.example.lifeline.mvp.models

import com.example.lifeline.data.User
import com.example.lifeline.utils.PrefHelper

class UserModel() {
    fun saveUser(user: User){
        PrefHelper.saveUserData(user)
    }

    fun getUser():User{
        return PrefHelper.getUserData()
    }

    fun getUserId():Long{
        return PrefHelper.getUserId()
    }
}