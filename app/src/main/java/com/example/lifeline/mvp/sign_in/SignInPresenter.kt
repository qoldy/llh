package com.example.lifeline.mvp.sign_in

import android.util.Log
import android.util.Patterns
import com.example.lifeline.R
import com.example.lifeline.data.User
import com.example.lifeline.mvp.models.UserModel
import com.example.lifeline.networking.RetrofitService
import com.example.lifeline.networking.post.request.SignInRequest
import com.example.lifeline.networking.post.response.SignInResponse
import com.example.lifeline.utils.PrefHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response

class SignInPresenter(private val signInView: SignInContract.View): SignInContract.Presenter {
    private lateinit var login:String
    private lateinit var password:String

    //Вход в аккаунт
    override fun signIn(){
        login=signInView.getLogin()
        password=signInView.getPassword()
        if(checkInput())
            trySignIn()
    }

    //Проверка ввода
    private fun checkInput():Boolean{
        //Пустые поля
        if(login==""){
            signInView.showError(signInView.getContext().getString(R.string.error_empty_username))
            return false
        }
        if(password==""){
            signInView.showError(signInView.getContext().getString(R.string.error_empty_password))
            return false
        }
        //Некорректная почта/логин
        val regex = Regex("^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
        if(!Patterns.EMAIL_ADDRESS.matcher(login).matches()&&!regex.matches(login)){
            signInView.showError(signInView.getContext().getString(R.string.error_not_correct_username))
            return false
        }
        //Некорректный пароль
        if(!regex.matches(password)){
            signInView.showError(signInView.getContext().getString(R.string.error_not_correct_password))
            return false
        }
        return true
    }

    //Запрос на вход в аккаунт
    private fun trySignIn(){
        val cd=CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .signIn(SignInRequest(login,password))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { response->onResponse(response) },
                                { error->onError(error) },
                        )
        )
    }

    //Ошибка в результате запроса
    private fun onError(error:Throwable) {
        signInView.showError(signInView.getContext().getString(R.string.error_internet_connection))
    }

    //Ответ от сервера
    private fun onResponse(response:Response<SignInResponse>) {
        //Корректный ответ
        if (response.code()==200) {
            val userModel = UserModel()
            userModel.saveUser(User(response.body()!!.id.toLong(), response.body()!!.username))
            PrefHelper.saveToken(response.body()!!.token)
            signInView.onSuccess()
            return
        }
        //Обработка ошибок
        when {
            //Пользователь не найден
            response.code()==403 -> {
                signInView.showError(signInView.getContext().getString(R.string.error_user_not_found))
            }
            //Неправильный пароль
            response.code()==401 -> {
                signInView.showError(signInView.getContext().getString(R.string.error_wrong_password))
            }
            //Иные ошибки, пока что списываем на соединение с сервером
            else -> {
                signInView.showError(signInView.getContext().getString(R.string.error_internet_connection))
            }
        }
    }
}