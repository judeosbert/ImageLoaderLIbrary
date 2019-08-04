package com.klepto.labs.imageloader

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.klepto.labs.imageloader.di.appModule
import com.klepto.labs.imageloader.model.Status
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class ImageLoader(private var context:Context?,
                   private var url:String?,
                   private var imageView: ImageView?):KoinComponent{
    private val dataHolder by inject<DataHolder>()
    fun startLoad(){

        url?.let {
            dataHolder.loadData(it){bitmap,status,t->
                if(status == Status.SUCCESS){
                    imageView?.setImageBitmap(bitmap)
                }else{
                    Toast.makeText(this.context,t?.message?:"",Toast.LENGTH_SHORT).show()
                }

            }
        }

    }



    data class Builder(
        private var context:Context?=null,
        private var url:String?=null,
        private var imageView: ImageView?=null
    ) {
        fun with(context: Context) = apply { this.context = context }
        fun load(url: String) = apply { this.url = url }
        fun into(imageView: ImageView) = apply {
            this.imageView = imageView
            ImageLoader(context, url, imageView).startLoad()
        }
    }


    companion object {
        var context:Context?=null
        fun init(appContext: Context) {
            this.context = appContext
            startKoin {
                androidContext(appContext)
                modules(appModule)
            }
        }
    }
}