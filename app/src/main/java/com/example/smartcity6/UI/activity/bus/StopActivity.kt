package com.example.smartcity6.UI.activity.bus

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.extension.sharedPreferences
import com.example.smartcity6.network.ServiceCreate
import com.example.smartcity6.network.Stop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StopActivity(context: Context) :PopupWindow(context) {
   init {
       val view = LayoutInflater.from(context).inflate(R.layout.activity_stop,null,false)
       val rv = view.findViewById<RecyclerView>(R.id.rv_stop)
       val id = sharedPreferences.getInt("stop_id",0)

       this?.apply {
           contentView = view
           setBackgroundDrawable(ColorDrawable(Color.WHITE))
           height = 500
           width = ViewGroup.LayoutParams.MATCH_PARENT
           isFocusable = true
           isClippingEnabled = true
       }
       ServiceCreate.smartCityCrete.getStop(id).enqueue(object :Callback<Stop>{
           override fun onFailure(p0: Call<Stop>, p1: Throwable) {
           }

           override fun onResponse(p0: Call<Stop>, p1: Response<Stop>) {
              val body = p1.body()
               if (body != null){
                   val adapter = ItemAdapter(R.layout.item_stop,body.rows,GG::class.java)
                   rv.layoutManager = LinearLayoutManager(context)
                   rv.adapter = adapter
               }
           }

       })

   }
}

class GG(view: View):ItemAdapter.MyViewHolder(view){
    val stop = view.findViewById<TextView>(R.id.stop_bus)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        stop.text = (data[position] as Stop.Row).name
    }

}