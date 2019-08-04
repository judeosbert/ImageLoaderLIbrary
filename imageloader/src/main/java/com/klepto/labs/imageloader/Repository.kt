package com.klepto.labs.imageloader

import android.graphics.BitmapFactory
import com.klepto.labs.imageloader.datasource.DataSource
import com.klepto.labs.imageloader.datasource.MemoryDataSource
import com.klepto.labs.imageloader.model.NetworkStatus
import com.klepto.labs.imageloader.model.ResponseModel
import com.klepto.labs.imageloader.network.CallManager
import com.klepto.labs.imageloader.network.services.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class Repository:KoinComponent {
    private val dataSource:DataSource by inject { parametersOf(ImageLoader.context) }
    fun loadData(key: String): Observable<ResponseModel>? {
        val memorySource = dataSource.getDataFromMemory(key)
        val diskSource = dataSource.getDataFromDisk(key)
        val networkSource = dataSource.getDataFromNetwork(key)

        return Observable.concat(memorySource,diskSource,networkSource)
            .firstElement()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }
}