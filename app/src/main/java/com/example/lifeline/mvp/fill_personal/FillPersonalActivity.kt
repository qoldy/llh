package com.example.lifeline.mvp.fill_personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.lifeline.R
import com.example.lifeline.mvp.fill_parameters.FillParametersActivity

class FillPersonalActivity:AppCompatActivity(), FillPersonalContract.View {
    private lateinit var fullNameField:EditText
    private lateinit var addressField:EditText
    private lateinit var policyField:EditText
    private lateinit var presenter: FillPersonalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fill_personal_activity)
        init()
    }
    private fun init(){
        presenter= FillPersonalPresenter(this)
        fullNameField=findViewById(R.id.full_name_field)
        addressField=findViewById(R.id.address_field)
        policyField=findViewById(R.id.policy_field)
    }

    fun onConfirmPersonalClicked(view:View){
        presenter.fillPersonal()
    }

    override fun showError() {

    }

    override fun onSuccess() {
        val intent=Intent(this, FillParametersActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun getFullName(): String {
        return fullNameField.text.toString()
    }

    override fun getAddress(): String {
        return addressField.text.toString()
    }

    override fun getPolicy(): String {
        return policyField.text.toString()
    }
}