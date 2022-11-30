package com.example.smartcity6.UI.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import com.example.smartcity6.R
import com.example.smartcity6.extension.edit
import com.example.smartcity6.extension.sharedPreferences

class IpActivity(context: Context) :PopupWindow(context) {
   init {
       val view = LayoutInflater.from(context).inflate(R.layout.activity_ip,null,false)
       val ip = view.findViewById<EditText>(R.id.et_ip)
       val dk = view.findViewById<EditText>(R.id.et_dk)
       val Save = view.findViewById<Button>(R.id.btn_save)

       this?.apply {
           width = ViewGroup.LayoutParams.MATCH_PARENT
           height = 700
           isFocusable = true
           isClippingEnabled = true
           contentView = view
           setBackgroundDrawable(ColorDrawable(Color.WHITE))
       }

       Save.setOnClickListener {
           val ip = ip.text.toString()
           val dk =dk.text.toString()
           sharedPreferences.edit {
               putString("ip",ip)
               putString("dk",dk)
           }
       }
   }
}