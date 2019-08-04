package com.klepto.labs.imageloader.datasource

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.klepto.labs.imageloader.model.NetworkStatus
import com.klepto.labs.imageloader.model.ResponseModel
import com.klepto.labs.imageloader.network.services.ApiService
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkDataSource:BaseDataSource(),KoinComponent {
    private val mApiService by inject<ApiService>()



    fun getData(url: String): BehaviorSubject<ResponseModel> {
        val returnSubject = BehaviorSubject.create<ResponseModel>()
        val disposable = mApiService.getImage(url)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        val bitmap = BitmapFactory.decodeStream(it.byteStream())
                        val responseModel = ResponseModel(-1,bitmap, NetworkStatus.SUCCESS)
                        returnSubject.onNext(responseModel)
                    },
                    onComplete = {
                        returnSubject.onComplete()
                    }

                )
        return returnSubject;
        }


}