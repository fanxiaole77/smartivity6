package com.example.smartcity6.UI.action

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.smartcity6.MainActivity
import com.example.smartcity6.R
import com.example.smartcity6.extension.edit
import com.example.smartcity6.extension.sharedPreferences
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.Login
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()


        login.setOnClickListener {
            val user = et_user.text.toString()
            val pass = et_pass.text.toString()
            ServiceCreate.smartCityCrete.postLogin(Login(pass,user)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                   val body = p1.body()
                    if (body != null){
                        if (body.code == 200){

                            sharedPreferences.edit {
                                putString("token",body.token)
                            }

                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))

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