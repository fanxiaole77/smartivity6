package com.example.smartcity6.UI.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.network.NewsContent
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_news_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)

        supportActionBar?.hide()

        val id = intent.getIntExtra("news_id",0)


        ServiceCreate.smartCityCrete.getNewsContent(id).enqueue(object:Callback<NewsContent>{
            override fun onFailure(p0: Call<NewsContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsContent>, p1: Response<NewsContent>) {
                val body = p1.body()
                if (body != null) {
                    title1.text = body.data.title
                    content1.text = Html.fromHtml(body.data.content)
                    Glide.with(this@NewsContentActivity).load(ServiceCreate.url + body.data.cover)
                        .into(iamge1)
                }
            }
        })

    }
}