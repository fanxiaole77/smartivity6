package com.example.smartcity6.extension

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.smartcity6.SmartCityApplication
import java.time.Duration

fun CharSequence.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(SmartCityApplication.context,this,duration).show()
}

fun SharedPreferences.edit(action:SharedPreferences.Editor.() -> Unit){
    val editor = edit()
    action(editor)
    editor.apply()
}

val sharedPreferences:SharedPreferences = SmartCityApplication.context.getSharedPreferences(
    "sharedPreferences",
    Context.MODE_APPEND
)