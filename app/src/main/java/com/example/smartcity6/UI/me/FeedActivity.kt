package com.example.smartcity6.UI.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity6.R
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.Feed
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.fragment_me.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        supportActionBar?.hide()

        e.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        e.setOnClickListener { finish() }

        save_feed.setOnClickListener {
            val feed = et_feed.text.toString()
            ServiceCreate.smartCityCrete.postFeed(Feed(feed,"")).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
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