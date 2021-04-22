package com.example.lifeline.mvp.starting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifeline.R
import com.example.lifeline.mvp.sign_in.SignInActivity
import com.example.lifeline.utils.LifeLine

class StartingActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.starting_activity)
        LifeLine.setContext(this.applicationContext)
        val intent= Intent(this, SignInActivity::class.java)
        startActivityForResult(intent, 0)
    }
}