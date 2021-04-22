package com.example.lifeline.mvp.fill_parameters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.example.lifeline.R
import com.example.lifeline.mvp.menu.MenuActivity
import java.time.LocalDate

class FillParametersActivity:AppCompatActivity(), FillParametersContract.View {
    private lateinit var dayPicker:NumberPicker
    private lateinit var monthPicker: NumberPicker
    private lateinit var yearPicker: NumberPicker
    private lateinit var maleBox: CheckBox
    private lateinit var femaleBox: CheckBox
    private lateinit var heightField: EditText
    private lateinit var weightField: EditText
    private lateinit var presenter:FillParametersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fill_parameters_activity)
        init()
    }

    private fun init(){
        dayPicker=findViewById(R.id.day_picker)
        monthPicker=findViewById(R.id.month_picker)
        yearPicker=findViewById(R.id.year_picker)
        maleBox=findViewById(R.id.male_box)
        femaleBox=findViewById(R.id.female_box)
        heightField=findViewById(R.id.height_field)
        weightField=findViewById(R.id.weight_field)
        presenter= FillParametersPresenter(this)
    }
    override fun showError() {
    }

    override fun onSuccess() {
        val intent= Intent(this, MenuActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun getDateOfBirth(): LocalDate {
        return LocalDate.of(yearPicker.value, monthPicker.value, dayPicker.value)
    }

    override fun getGender(): String {
        return if(maleBox.isChecked) "Мужской" else "Женский"
    }

    override fun getHeight(): String {
        return heightField.text.toString()
    }

    override fun getWeight(): String {
        return weightField.text.toString()
    }

    fun onConfirmParametersClicked(view: View){
        presenter.fillParameters()
    }
}