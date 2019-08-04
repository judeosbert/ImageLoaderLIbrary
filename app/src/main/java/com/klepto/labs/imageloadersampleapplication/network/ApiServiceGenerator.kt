package com.klepto.labs.imageloadersampleapplication.network
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceGenerator{
    companion object{
        fun create(): ApiService {
            val httpClient = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}