package com.klepto.labs.imageloadersampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.imageloader.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageList.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        imageList.adapter = ListAdapter()
    }
}
