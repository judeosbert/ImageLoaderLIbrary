package com.klepto.labs.imageloader.datasource

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.klepto.labs.imageloader.model.Status
import com.klepto.labs.imageloader.model.ResponseModel
import com.klepto.labs.imageloader.model.SOURCE_DISK
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class DiskDataSource(private val appContext:Context):KoinComponent, BaseDataSource() {

    private val mMemoryCache by inject<MemoryDataSource>()
    private val DISK_CACHE_SUBDIR = "thumbnails"
    private val cacheDir:File
    init {
        cacheDir = getDiskCacheDir(appContext,DISK_CACHE_SUBDIR)
    }
    fun hasData(key: String):Boolean {
        val file = File(cacheDir,getFileName(key))
        return file.exists()
    }
    fun getData(key: String):Observable<ResponseModel> {
        return Observable.create {
            if(hasData(key)) {
                val file = File(cacheDir, getFileName(key))
                val bitmap = BitmapFactory.decodeFile(file.path)
                mMemoryCache.put(key, bitmap)
                val responseModel = ResponseModel(SOURCE_DISK,bitmap,Status.SUCCESS)
                it.onNext(responseModel)
            }
            it.onComplete()
        }
    }

    fun setData(key: String,bitmap:Bitmap) {
        val disposible = Observable.fromCallable {
            val file  = File(cacheDir,getFileName(key))
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,0,bos)
            val bitmapData = bos.toByteArray()

            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        }.subscribeOn(Schedulers.io())
            .subscribe {
                Log.d("IMAGE_TAG","Memory cache Complete")
            }



    }



    private fun getDiskCacheDir(context: Context, uniqueName:String): File {

        val cachePath =
            if(Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
            || Environment.isExternalStorageRemovable()){
                context.externalCacheDir.path
        }else{
                context.cacheDir.path
            }
        val file = File(cachePath+File.separator+uniqueName)
        if(!file.exists())
            file.mkdirs()
        return file
    }



}