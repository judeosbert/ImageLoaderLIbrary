package com.klepto.labs.imageloadersampleapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.klepto.labs.imageloader.ImageLoader

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    val urls:List<String> = createList()
    private lateinit var mContext:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mContext = parent.context
        val view  = LayoutInflater.from(mContext).inflate(R.layout.rv_item,parent,false);

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int  = urls.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        ImageLoader.Builder()
            .with(mContext)
            .load(urls.get(position))
            .into(holder.imageHolder)
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imageHolder:ImageView = view.findViewById(R.id.imageView)
    }

    private fun createList(): MutableList<String> {
        val list = mutableListOf<String>()
        for(i in (1..20)){
            list.add("https://picsum.photos/200/300?random=$i")
        }
        return list
    }
}