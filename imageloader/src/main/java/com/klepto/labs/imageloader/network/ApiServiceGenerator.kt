package com.klepto.labs.imageloader.network

import com.klepto.labs.imageloader.network.services.ApiService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class ApiServiceGenerator{
    companion object{
        fun create(): ApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(loggingInterceptor)
            val retrofit = Retrofit.Builder()
                .baseUrl("https:www.google.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}