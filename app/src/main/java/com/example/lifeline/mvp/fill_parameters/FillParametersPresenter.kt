package com.example.lifeline.mvp.fill_parameters

import com.example.lifeline.data.Parameters
import com.example.lifeline.mvp.BasicPresenter
import com.example.lifeline.mvp.models.ParametersModel
import com.example.lifeline.mvp.models.UserModel

class FillParametersPresenter(private val fillParametersView:FillParametersContract.View):FillParametersContract.Presenter, BasicPresenter {
    private val parametersModel=ParametersModel()
    private val userModel=UserModel()
    fun fillParameters(){
        var dateOfBirth=fillParametersView.getDateOfBirth()
        var gender=fillParametersView.getGender()
        var height=fillParametersView.getHeight().toInt()
        var weight=fillParametersView.getWeight().toDouble()

        parametersModel.putParameters(
                userModel.getUserId(),
                Parameters(dateOfBirth,gender,height,weight),
                this
        )
    }
    override fun onError() {
        fillParametersView.showError()
    }

    override fun onResponse() {
        fillParametersView.onSuccess()
    }
}