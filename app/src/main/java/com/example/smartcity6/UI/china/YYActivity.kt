package com.example.smartcity6.UI.china

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import kotlinx.android.synthetic.main.activity_busorder.*
import kotlinx.android.synthetic.main.activity_y_y.*

/**
 * 预约养老
 */

class YYActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_y_y)

        supportActionBar?.hide()

        er.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        er.setOnClickListener { finish() }

        btn_3q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_4q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_5q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_6q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_7q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_8q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }


        btn_9q.setOnClickListener {
            startActivity(Intent(this,ContentActivity::class.java))
        }

    }
}