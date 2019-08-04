package com.klepto.labs.imageloader.model

import android.graphics.Bitmap

data class ResponseModel(var source:Int, var bitmap: Bitmap?=null, val networkStatus:Status, val throwable: Throwable?=null)

enum class Status{
    FAILED,SUCCESS
}

val SOURCE_NET = 0
val SOURCE_DISK = 1
val SOURCE_MEMORY = 2