package com.example.smartcity6.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class ItemAdapter<T: ItemAdapter.MyViewHolder>(
    val id:Int,
    val data: List<Any?>,
    val classViewHolder:Class<T>,
    val lisSize:Int = data.size,
    val list: List<Any?> = listOf()
):RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    abstract class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        abstract fun binViewHOlder(data: List<Any?>, position: Int,list: List<Any?>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(id,null,false)
        val cord = classViewHolder.getConstructor(View::class.java)
        return cord.newInstance(view)
    }

    override fun getItemCount(): Int {
        return lisSize
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binViewHOlder(data, position, list)
    }

}