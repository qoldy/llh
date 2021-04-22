package com.example.lifeline.mvp.models

import com.example.lifeline.data.Pulse
import com.example.lifeline.mvp.BasicPresenter
import com.example.lifeline.networking.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PulseModel {
    fun postPulse(pulse: Pulse, presenter:BasicPresenter){
        val cd=CompositeDisposable()
        cd.add(
                RetrofitService.getInstance()
                        .postPulse(pulse)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {onPostResponse(presenter)},
                                {error->onPostError(error,presenter)}
                        )
        )
    }
    private fun onPostError(error:Throwable, presenter: BasicPresenter){
         presenter.onResponse()
    }
    private fun onPostResponse(presenter: BasicPresenter){
        presenter.onError()
    }
}