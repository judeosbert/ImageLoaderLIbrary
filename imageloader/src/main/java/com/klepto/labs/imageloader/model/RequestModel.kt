package com.klepto.labs.imageloader.model

import android.graphics.Bitmap
import android.net.Uri

data class ResponseModel(var callId:Int = -1,var bitmap: Bitmap?=null,val networkStatus:NetworkStatus,val throwable: Throwable?=null)

enum class NetworkStatus{
    FAILED,SUCCESS
}