package com.example.smartcity6.UI.activity.bus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcity6.R
import kotlinx.android.synthetic.main.activity_bus_all.*
import kotlinx.android.synthetic.main.activity_bus_date.*
import kotlinx.android.synthetic.main.activity_login.*

class BusAllActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_all)

        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        supportActionBar?.hide()

        btn_3.setOnClickListener {
            val name = et_name.text.toString()
            val phone = et_phone.text.toString()
            val start = et_start.text.toString()
            val end = et_end.text.toString()
            Log.d("name",name)
            startActivity(Intent(this,BusorderActivity::class.java).apply {
                putExtra("name",name)
                putExtra("phone",phone)
                putExtra("start",start)
                putExtra("end",end)
                putExtra("date1",date)
                putExtra("time1",time)
            })
        }

    }
}