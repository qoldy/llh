package com.example.lifeline.mvp.sign_in

import android.util.Log
import com.example.lifeline.data.User
import com.example.lifeline.mvp.models.UserModel
import com.example.lifeline.networking.RetrofitService
import com.example.lifeline.networking.post.request.SignInRequest
import com.example.lifeline.networking.post.response.SignInResponse
import com.example.lifeline.utils.PrefHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignInPresenter(private val signInView: SignInContract.View): SignInContract.Presenter {
    private lateinit var login:String
    private lateinit var password:String

    override fun signIn(){
        login=signInView.getLogin()
        password=signInView.getPassword()
        if(login==""||password==""){
            signInView.showError()
            return
        }
        trySignIn()
    }

    private fun trySignIn(){
        val cd=CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .signIn(SignInRequest(login,password))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { response->onResponse(response) },
                                { error->onError(error) }
                        )
        )
    }

    private fun onError(error:Throwable) {
        Log.v("error", error.message.toString())
        signInView.showError()
    }

    private fun onResponse(response:SignInResponse) {
        val userModel=UserModel()
        userModel.saveUser(User(response.id.toLong(),response.username))
        PrefHelper.saveToken(response.token)
        signInView.onSuccess()
    }
}