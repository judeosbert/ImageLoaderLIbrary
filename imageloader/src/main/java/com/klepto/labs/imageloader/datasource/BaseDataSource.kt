package com.klepto.labs.imageloader.datasource


abstract class BaseDataSource{
    fun getFileName(key:String) = key.hashCode().toString()
}

