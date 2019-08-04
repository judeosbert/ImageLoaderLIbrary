package com.klepto.labs.imageloadersampleapplication

import android.app.Application
import com.klepto.labs.imageloader.ImageLoader

class BaseApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        ImageLoader.init( this)
    }
}
