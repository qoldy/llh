package com.example.lifeline.mvp.sign_up

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.lifeline.R

class SignUpActivity:AppCompatActivity(), SignUpContract.View {
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var emailField: EditText
    private lateinit var confirmField: EditText
    private lateinit var presenter: SignUpPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)
        init()
    }

    private fun init(){
        loginField=findViewById(R.id.login_field)
        emailField=findViewById(R.id.email_field)
        passwordField=findViewById(R.id.password_field)
        confirmField=findViewById(R.id.confirm_field)
        presenter= SignUpPresenter(this)
    }

    fun onConfirmSignUpClicked(view: View){
        presenter.signUp()
    }

    override fun showError() {

    }

    override fun onSuccess() {
    }

    override fun getEmail(): String {
        return emailField.text.toString()
    }

    override fun getLogin(): String {
        return loginField.text.toString()
    }

    override fun getPassword(): String {
        return passwordField.text.toString()
    }

    override fun getConfirm(): String {
        return confirmField.text.toString()
    }
}