package com.klepto.labs.imageloader

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.klepto.labs.imageloader.di.appModule
import com.klepto.labs.imageloader.model.NetworkStatus
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class ImageLoader( private var context:Context?,
                   private var url:String?,
                   private var imageView: ImageView?):KoinComponent{
    private val dataHolder by inject<DataHolder>()
    fun startLoad(){
        url?.let {
            dataHolder.loadData(it){bitmap,status,t->
                if(status == NetworkStatus.SUCCESS){
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
        lateinit var context:Context
        fun init(appContext: Context) {
            startKoin {
                context = appContext
                androidContext(context)
                modules(appModule)
            }
        }
    }
}