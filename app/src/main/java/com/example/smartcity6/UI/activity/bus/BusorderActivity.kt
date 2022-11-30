package com.example.smartcity6.UI.activity.bus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.AddBus
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_bus_all.*
import kotlinx.android.synthetic.main.activity_bus_content.*
import kotlinx.android.synthetic.main.activity_busorder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusorderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busorder)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val start = intent.getStringExtra("start")
        val end = intent.getStringExtra("end")
        val date = intent.getStringExtra("date1")
        val time = intent.getStringExtra("time1")

        supportActionBar?.hide()
        s.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        s.setOnClickListener { finish() }

        tv_name12.text = name
        tv_phone12.text = phone
        tv_start12.text = start
        tv_end12.text = start
        tv_time12.text = date+time

        btn_add.setOnClickListener {
            ServiceCreate.smartCityCrete.postBus(AddBus(end,null,null,start,1)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}