package com.klepto.labs.imageloadersampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.imageloadersampleapplication.network.ApiService
import com.klepto.labs.imageloadersampleapplication.network.ApiServiceGenerator
import com.klepto.labs.imageloadersampleapplication.network.model.ResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val  adapter  = ListAdapter(onClicked = {handlePhotoClicked(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageList.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        imageList.adapter = adapter
        getImages()
    }

    fun handlePhotoClicked(it: String){
        val instance = PhotoPreviewFragment.newInstance(it);
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame,instance)
            .addToBackStack(it).commit()
    }

    fun getImages(){
        val apiService: ApiService = ApiServiceGenerator.create()
        apiService.getPhotos().enqueue(object : Callback<List<ResponseModel>> {
            override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                Log.d("JUDE","Failed")
            }

            override fun onResponse(call: Call<List<ResponseModel>>, unsplashResponse: Response<List<ResponseModel>>) {
                if(unsplashResponse.isSuccessful){
                    val responseModel = unsplashResponse.body();
                    val urlList = mutableListOf<String>()
                    responseModel?.let {
                        it.forEach {model->
                            model.urls?.regular?.let {url->
                                urlList.add(url)
                            }
                        }
                        adapter.urls = urlList
                    }
                }
            }

        })
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        if(backStackCount == 1){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }
}
