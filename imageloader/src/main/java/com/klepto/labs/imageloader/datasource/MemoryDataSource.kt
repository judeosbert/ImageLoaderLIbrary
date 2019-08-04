package com.klepto.labs.imageloader.datasource

import android.graphics.Bitmap
import android.util.LruCache
import com.klepto.labs.imageloader.model.Status
import com.klepto.labs.imageloader.model.ResponseModel
import com.klepto.labs.imageloader.model.SOURCE_MEMORY
import io.reactivex.Observable


class MemoryDataSource(maxSize:Int = (Runtime.getRuntime().maxMemory() / 1024).toInt())
    :LruCache<String,Bitmap>(maxSize)
     {
    fun hasData(key: String): Boolean {
        val bitmap = this.get(getFileName(key))
        return bitmap != null && !bitmap.isRecycled
    }

    fun getData(key: String): Observable<ResponseModel> {

        return Observable.create {
            if(hasData(key)) {
                val responseModel = ResponseModel(SOURCE_MEMORY, get(getFileName(key)), Status.SUCCESS)
                it.onNext(responseModel)
            }
            it.onComplete()
        }
    }

    fun setData(key:String,bitmap:Bitmap) = this.put(getFileName(key),bitmap)

         fun getFileName(key:String) = key.hashCode().toString()

}
