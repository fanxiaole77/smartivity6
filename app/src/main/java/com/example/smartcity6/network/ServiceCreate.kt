package com.example.smartcity6.network

import com.example.smartcity6.extension.sharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreate {

    val ip = sharedPreferences.getString("ip","")
    val dk = sharedPreferences.getString("dk","")
    val url = "http://$ip:$dk/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T>create(service:Class<T>):T = retrofit.create(service)

    val smartCityCrete = create(SmartCityApi::class.java)

}