package com.example.smartcity6.UI.activity.bus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.BusContent
import com.example.smartcity6.network.ServiceCreate
import com.example.smartcity6.network.Stop
import kotlinx.android.synthetic.main.activity_bus_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_content)

        supportActionBar?.hide()
        o.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        o.setOnClickListener { finish() }

        val id = intent.getIntExtra("bus_content_id",0)
        ServiceCreate.smartCityCrete.getBusContent(id).enqueue(object :Callback<BusContent>{
            override fun onFailure(p0: Call<BusContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<BusContent>, p1: Response<BusContent>) {
                val body = p1.body()
                if (body != null){
                    tv_start.text = body.data.first
                    tv_end.text = body.data.end
                    tv_money.text = body.data.price.toString()
                    tv_mile.text = body.data.mileage

                }
            }

        })

        ServiceCreate.smartCityCrete.getStop(id).enqueue(object :Callback<Stop>{
            override fun onFailure(p0: Call<Stop>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<Stop>, p1: Response<Stop>) {
               val body =p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_bus_content,body.rows,HH::class.java)
                    rv_zed.layoutManager = LinearLayoutManager(this@BusContentActivity).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                    rv_zed.adapter = adapter
                }
            }

        })
        btn_1.setOnClickListener {
            startActivity(Intent(this,BusDateActivity::class.java))
        }


    }
}

class HH(view: View):ItemAdapter.MyViewHolder(view){
    val text:TextView = view.findViewById(R.id.tv_bus111)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        text.text = (data[position] as Stop.Row).name
    }

}