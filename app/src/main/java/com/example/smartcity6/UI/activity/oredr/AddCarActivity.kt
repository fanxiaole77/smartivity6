package com.example.smartcity6.UI.activity.oredr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.Binding
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_add_car.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        supportActionBar?.hide()

        btn_4.setOnClickListener {
             val chejia = et_enNo.text.toString()
             val chepai = et_plNo.text.toString()
             val type = et_type.text.toString()

            ServiceCreate.smartCityCrete.postBinding(Binding(chepai,chejia,type)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                val body =p1.body()
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