package com.example.smartcity6.UI.activity.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.smartcity6.R
import com.example.smartcity6.network.ActivityContent
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_vo_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vo_content)

        val id = intent.getIntExtra("vo_id",0)

        supportActionBar?.hide()

        ServiceCreate.smartCityCrete.getActivityContent(id).enqueue(object :Callback<ActivityContent>{
            override fun onFailure(p0: Call<ActivityContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<ActivityContent>, p1: Response<ActivityContent>) {
                val body = p1.body()
                if (body != null){
                    biaoti.text = body.data.title
                    neirong.text = Html.fromHtml(body.data.detail)
                }
            }

        })

    }
}