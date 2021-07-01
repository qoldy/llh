package com.example.lifeline.mvp.sign_up

import android.util.Log
import android.util.Patterns
import com.example.lifeline.R
import com.example.lifeline.data.User
import com.example.lifeline.mvp.models.UserModel
import com.example.lifeline.networking.RetrofitService
import com.example.lifeline.networking.post.request.SignUpRequest
import com.example.lifeline.networking.post.response.SignUpResponse
import com.example.lifeline.utils.PrefHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SignUpPresenter(private val signUpView:SignUpContract.View):SignUpContract.Presenter {
    private lateinit var login:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var confirm:String

    //Регистрация
    override fun signUp() {
        login=signUpView.getLogin()
        email=signUpView.getEmail()
        password=signUpView.getPassword()
        confirm=signUpView.getConfirm()

        if(checkInput())
            trySignUp()
    }

    //Проверка ввода
    private fun checkInput():Boolean{
        //Пустые поля
        if(login=="") {signUpView.showError(signUpView.getContext().getString(R.string.err_reg_empty_username)); return false}
        if(email=="") {signUpView.showError(signUpView.getContext().getString(R.string.err_reg_empty_email)); return false}
        if(password=="") {signUpView.showError(signUpView.getContext().getString(R.string.err_reg_empty_password)); return false}

        //Некорректный email
        if(!Patterns.EMAIL_ADDRESS.matcher(login).matches()) {
            signUpView.showError(signUpView.getContext().getString(R.string.err_reg_email_not_match));
            return false
        }

        //Некорректный логин
        val regex = Regex("^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
        if(!regex.matches(login)||login.length<5){
            signUpView.showError(signUpView.getContext().getString(R.string.err_reg_login_not_match));
            return false
        }

        //Некорректный пароль
        if(!regex.matches(password)||password.length<5){
            signUpView.showError(signUpView.getContext().getString(R.string.err_reg_password_not_match));
            return false
        }

        //Пароли не совпадают
        if(password!=confirm){
            signUpView.showError(signUpView.getContext().getString(R.string.err_reg_password_not_confirmed));
            return false
        }
        return true
    }

    //Попытка регистрации
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

    //Ошибка при запросе
    private fun onError(error:Throwable){
        signUpView.showError(signUpView.getContext().getString(R.string.err_internet_connection))
    }

    //При получении ответа
    private fun onResponse(response: Response<SignUpResponse>){
        when (response.body()!!.status) {
            "200" -> {
                val userModel = UserModel()
                userModel.saveUser(User(response.body()!!.id.toLong(), response.body()!!.username))
                PrefHelper.saveToken(response.body()!!.token)
                signUpView.onSuccess()
            }
            //Существует email
            "230" -> signUpView.showError(signUpView.getContext().getString(R.string.err_reg_email_exist))
            //Существует логин
            "231" -> signUpView.showError(signUpView.getContext().getString(R.string.err_reg_username_exist))
        }

        //Остальные ошибки, пока что списываем на соединение с интернетом
        if (response.code()!=200) signUpView.showError(signUpView.getContext().getString(R.string.err_internet_connection))
    }
}