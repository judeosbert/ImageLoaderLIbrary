package com.klepto.labs.imageloader

import com.klepto.labs.imageloader.datasource.DataSource
import com.klepto.labs.imageloader.model.ResponseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class Repository:KoinComponent {
    private val dataSource:DataSource by inject { parametersOf(ImageLoader.context) }
    fun loadData(key: String): Observable<ResponseModel>? {

        return if(dataSource.isPresentInMemory(key)){
            dataSource.getDataFromMemory(key)
        } else if(dataSource.isPresentInDisk(key)){
            dataSource.getDataFromDisk(key)
        } else{
            dataSource.getDataFromNetwork(key)
        }
    }
}