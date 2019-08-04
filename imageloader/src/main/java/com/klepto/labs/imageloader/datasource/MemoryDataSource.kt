package com.klepto.labs.imageloader.datasource

import android.graphics.Bitmap
import android.util.LruCache
import com.klepto.labs.imageloader.model.NetworkStatus
import com.klepto.labs.imageloader.model.ResponseModel
import io.reactivex.Observable
import retrofit2.Response
import java.util.*


class MemoryDataSource(maxSize:Int = (Runtime.getRuntime().maxMemory() / 1024).toInt())
    :LruCache<String,Bitmap>(maxSize)
     {
    fun hasData(key: String): Boolean {
        val bitmap = this.get(key)
        return bitmap != null && !bitmap.isRecycled
    }

    fun getData(key: String): Observable<ResponseModel> {

        return Observable.create {
            if(hasData(key)) {
                val responseModel = ResponseModel(-1, get(key), NetworkStatus.SUCCESS)
                it.onNext(responseModel)
            }
            it.onComplete()
        }
    }

    fun setData(key:String,bitmap:Bitmap) = this.put(key,bitmap)

}
