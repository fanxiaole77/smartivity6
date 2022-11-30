package com.example.smartcity6.UI.china

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.smartcity6.R

/**
 *健康评估
 */

class PhActivity : AppCompatActivity() {

    val array1 = arrayOf("正常","痴呆","抑郁","暴力")
    val array2 = arrayOf("正常","拄拐","轮椅","卧床")
    val array3 = arrayOf("饮食","洗澡","穿衣","修饰")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ph)
        supportActionBar?.hide()

        val sp1:Spinner = findViewById(R.id.sp1)
        val sp2:Spinner = findViewById(R.id.sp2)
        val sp3:Spinner = findViewById(R.id.sp3)

        val arrayAdapter1 = ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,array1)
        val arrayAdapter2 = ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,array2)
        val arrayAdapter3 = ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,array3)

        sp1.adapter = arrayAdapter1
        sp2.adapter = arrayAdapter2
        sp3.adapter = arrayAdapter3

    }
}