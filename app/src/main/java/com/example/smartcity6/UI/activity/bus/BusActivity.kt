package com.example.smartcity6.UI.activity.bus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.extension.edit
import com.example.smartcity6.extension.sharedPreferences
import com.example.smartcity6.network.BusList
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_bus.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        supportActionBar?.hide()
        y.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        y.setOnClickListener { finish() }

        ServiceCreate.smartCityCrete.getBusList().enqueue(object :Callback<BusList>{
            override fun onFailure(p0: Call<BusList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<BusList>, p1: Response<BusList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_bus,body.rows,FF::class.java)
                    rv_bus.layoutManager = LinearLayoutManager(this@BusActivity)
                    rv_bus.adapter = adapter
                }
            }

        })

    }
}

class FF(val view: View):ItemAdapter.MyViewHolder(view){
    val start = view.findViewById<TextView>(R.id.bus_start)
     val end = view.findViewById<TextView>(R.id.bus_end)
    val money = view.findViewById<TextView>(R.id.bus_money)
    val mile = view.findViewById<TextView>(R.id.bus_mile)
    val image = view.findViewById<ImageButton>(R.id.down)
    val text = view.findViewById<TextView>(R.id.ceshi)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        start.text = (data[position] as BusList.Row).first
        end.text = (data[position] as BusList.Row).end
        money.text = (data[position] as BusList.Row).price.toString()
        mile.text = (data[position] as BusList.Row).mileage
        image.setOnClickListener {
            sharedPreferences.edit {
                putInt("stop_id",(data[position] as BusList.Row).id)
            }

        }
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,BusContentActivity::class.java).apply {
                putExtra("bus_content_id",(data[position] as BusList.Row).id)
            })
        }
    }
}