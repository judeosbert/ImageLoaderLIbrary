package com.klepto.labs.imageloadersampleapplication.network

import com.klepto.labs.imageloadersampleapplication.network.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("photos?client_id=2651d4077291d821511d9d3bcf283241263d280e244a3c9f51b3b4aaeae0d099")
    fun getPhotos(): Call<List<ResponseModel>>
}

