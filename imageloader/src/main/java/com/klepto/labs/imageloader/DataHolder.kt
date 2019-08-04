package com.klepto.labs.imageloader

import android.graphics.Bitmap
import android.util.Log
import com.klepto.labs.imageloader.model.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.KoinComponent

class DataHolder(private val repository: Repository):KoinComponent {
    private val bin = CompositeDisposable()
    fun loadData(url: String,onChange:(bitmap: Bitmap?, Status, t:Throwable?)->Unit){
        val disposable = repository.loadData(url)?.let {observable->
            observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                onChange(it.bitmap,it.networkStatus,it.throwable)
                }
            ,onError = {it.printStackTrace()},
                onComplete = {Log.d("JUDE","Complete")}
                )}

        disposable?.let {
            bin.add(it)
        }

    }


}