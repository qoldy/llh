package com.example.lifeline.mvp.fill_personal

import com.example.lifeline.data.Personal
import com.example.lifeline.mvp.BasicPresenter
import com.example.lifeline.mvp.models.PersonalModel
import com.example.lifeline.mvp.models.UserModel

class FillPersonalPresenter(private val fillPersonalView:FillPersonalContract.View):FillPersonalContract.Presenter, BasicPresenter {
    private val personalModel=PersonalModel()
    private val userModel=UserModel()
    fun fillPersonal() {
        var fullName=fillPersonalView.getFullName()
        var address=fillPersonalView.getAddress()
        var policy=fillPersonalView.getPolicy()
        personalModel.putPersonal(userModel.getUserId(),
                Personal(fullName, address, policy),
                this
        )
    }

    override fun onError() {
        fillPersonalView.showError()
    }

    override fun onResponse() {
        fillPersonalView.onSuccess()
    }
}