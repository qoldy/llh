package com.example.lifeline.mvp.models

import com.example.lifeline.data.Parameters
import com.example.lifeline.mvp.BasicPresenter
import com.example.lifeline.mvp.fill_parameters.FillParametersContract
import com.example.lifeline.networking.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ParametersModel {
    fun putParameters(id:Long, request:Parameters, presenter:BasicPresenter){
        val cd= CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .putParameters(id, request)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {response->onPutResponse(response, presenter)},
                                { error->onPutError(error, presenter)}
                        )
        )
    }

    private fun onPutError(error:Throwable, presenter: BasicPresenter){
        presenter.onError()
    }

    private fun onPutResponse(response: Parameters, presenter: BasicPresenter){
        presenter.onResponse()
    }
}