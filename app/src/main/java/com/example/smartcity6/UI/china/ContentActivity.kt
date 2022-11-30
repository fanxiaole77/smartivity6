package com.example.smartcity6.UI.china

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        supportActionBar?.hide()
    }
}