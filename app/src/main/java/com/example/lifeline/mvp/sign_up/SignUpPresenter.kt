package com.example.lifeline.mvp.sign_up

import android.util.Log
import com.example.lifeline.data.User
import com.example.lifeline.mvp.models.UserModel
import com.example.lifeline.networking.RetrofitService
import com.example.lifeline.networking.post.request.SignUpRequest
import com.example.lifeline.networking.post.response.SignUpResponse
import com.example.lifeline.utils.PrefHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignUpPresenter(private val signUpView:SignUpContract.View):SignUpContract.Presenter {
    private lateinit var login:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var confirm:String

    override fun signUp() {
        login=signUpView.getLogin()
        email=signUpView.getEmail()
        password=signUpView.getPassword()
        confirm=signUpView.getConfirm()
        trySignUp()
    }

    private fun trySignUp(){
        val cd=CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .signUp(SignUpRequest(login, email, password))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { response->onResponse(response) },
                                { error->onError(error) }
                        )
        )
    }

    private fun onError(error:Throwable){
        Log.v("error", error.message.toString())
        signUpView.showError()
    }

    private fun onResponse(response:SignUpResponse){
        if(response.status=="200") {
            val userModel = UserModel()
            userModel.saveUser(User(response.id.toLong(), response.username))
            PrefHelper.saveToken(response.token)
            signUpView.onSuccess()
        }
    }
}