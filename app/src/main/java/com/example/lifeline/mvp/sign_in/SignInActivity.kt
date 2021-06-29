package com.example.lifeline.mvp.sign_in

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lifeline.R
import com.example.lifeline.mvp.menu.MenuActivity

class SignInActivity:AppCompatActivity(), SignInContract.View {
    private lateinit var loginField:EditText
    private lateinit var passwordField:EditText
    private lateinit var errorText:TextView
    private lateinit var presenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        init()
    }

    private fun init(){
        loginField=findViewById(R.id.login_field)
        passwordField=findViewById(R.id.password_field)
        errorText=findViewById(R.id.error_text)
        presenter= SignInPresenter(this)
    }

    fun onSignInClicked(view: View){
        errorText.text=""
        presenter.signIn()
    }

    override fun showError(error:String) {
        errorText.text=error
    }

    override fun onSuccess()  {
        val intent = Intent(this, MenuActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun getLogin(): String { return loginField.text.toString() }

    override fun getPassword(): String { return passwordField.text.toString() }

    override fun getContext(): Context { return this }
}