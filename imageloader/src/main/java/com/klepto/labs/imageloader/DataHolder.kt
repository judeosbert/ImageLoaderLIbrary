package com.klepto.labs.imageloader

import android.graphics.Bitmap
import android.util.Log
import com.klepto.labs.imageloader.model.NetworkStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent

class DataHolder(private val repository: Repository):KoinComponent {
    private val IMAGE_TAG=  "IMAGE_TAG"
    private val bin = CompositeDisposable()
    fun loadData(url: String,onChange:(bitmap: Bitmap?, NetworkStatus,t:Throwable?)->Unit){
        val disposable = repository.loadData(url)?.let {observable->
            observable.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onChange(it.bitmap,it.networkStatus,it.throwable)
        }}

        disposable?.let {
            bin.add(it)
        }

    }


}