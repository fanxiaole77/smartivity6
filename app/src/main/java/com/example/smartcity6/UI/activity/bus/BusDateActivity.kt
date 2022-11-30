package com.example.smartcity6.UI.activity.bus

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import com.example.smartcity6.extension.sharedPreferences
import kotlinx.android.synthetic.main.activity_bus_date.*
import java.sql.Time
import java.util.*

class BusDateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_date)

        supportActionBar?.hide()

        p.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        p.setOnClickListener { finish() }

        btn_date.setOnClickListener {
            val likne = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val date = "${year}-${month+1}-${dayOfMonth}"
                et_date.setText(date)
            }
            val dialog = DatePickerDialog(this,likne,Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)
            dialog.show()
        }

        btn_time.setOnClickListener {
            val line = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val time = "$hourOfDay:$minute"
                et_time.setText(time)
            }
            val time = TimePickerDialog(this,line,Calendar.HOUR_OF_DAY,Calendar.MINUTE,true)
            time.show()
        }

        btn_2.setOnClickListener {
            val date= et_date.text.toString()
            val time= et_time.text.toString()
            startActivity(Intent(this,BusAllActivity::class.java).apply {
                putExtra("date",date)
                putExtra("time",time)
            })
        }

    }
}