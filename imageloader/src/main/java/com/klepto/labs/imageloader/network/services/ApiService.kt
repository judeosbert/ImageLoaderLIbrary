package com.klepto.labs.imageloader.network.services
import io.reactivex.Observable
import okhttp3.ResponseBody

import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {
    @Streaming
    @GET
    fun getImage(@Url imageUrl:String): Observable<ResponseBody>
}