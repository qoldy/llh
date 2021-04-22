package com.example.lifeline.mvp.models

import com.example.lifeline.data.Personal
import com.example.lifeline.mvp.BasicPresenter
import com.example.lifeline.mvp.fill_personal.FillPersonalContract
import com.example.lifeline.networking.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PersonalModel {
    fun putPersonal(id:Long, request: Personal, presenter:BasicPresenter){
        val cd=CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .putPersonal(id, request)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {response->onPutResponse(response, presenter)},
                                { error->onPutError(error, presenter)}
                        )
        )
    }
    private fun onPutError(error:Throwable, presenter:BasicPresenter){
        presenter.onError()
    }

    private fun onPutResponse(response:Personal, presenter:BasicPresenter){
        presenter.onResponse()
    }
}