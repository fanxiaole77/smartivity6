package com.example.smartcity6.UI.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.Pass
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)
        supportActionBar?.hide()
        w.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        w.setOnClickListener { finish() }

        save_pass.setOnClickListener {
            val ord = et_ord.text.toString()
            val new = et_new.text.toString()

            ServiceCreate.smartCityCrete.putPass(Pass(new,ord)).enqueue(object :Callback<Message>{
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