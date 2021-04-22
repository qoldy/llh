package com.example.lifeline.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.lifeline.data.User

class PrefHelper {
    companion object{
        private const val PREF="pref"
        private const val PREF_TOKEN="token"
        private const val PREF_LOGIN="login"
        private const val PREF_ID="id"
        private const val PREF_EMAIL="email"

        fun saveToken(token:String){
            val sharedPreferences = LifeLine.getContext().getSharedPreferences(PREF,Context.MODE_PRIVATE)
            sharedPreferences.edit()
                    .putString(PREF_TOKEN, token)
                    .apply()
        }

        fun getToken(): String? {
            val sharedPreferences = LifeLine.getContext().getSharedPreferences(PREF,Context.MODE_PRIVATE)
            return sharedPreferences.getString(PREF_TOKEN,"token")
        }

        fun saveUserData(user: User){
            val sharedPreferences = LifeLine.getContext().getSharedPreferences(PREF,Context.MODE_PRIVATE)
            sharedPreferences.edit()
                    .putLong(PREF_ID,user.id)
                    .putString(PREF_LOGIN, user.login)
                    .apply()
        }

        fun getUserData():User{
            val sharedPreferences = LifeLine.getContext().getSharedPreferences(PREF,Context.MODE_PRIVATE)
            val login:String = if(sharedPreferences.getString(PREF_LOGIN,"login")==null){
                "login"
            }
            else
                sharedPreferences.getString(PREF_LOGIN,"login")!!
            return User(sharedPreferences.getLong(PREF_ID, 0), login)
        }

        fun getUserId():Long{
            val sharedPreferences = LifeLine.getContext().getSharedPreferences(PREF,Context.MODE_PRIVATE)
            return sharedPreferences.getLong(PREF_ID, 0)
        }
    }
}