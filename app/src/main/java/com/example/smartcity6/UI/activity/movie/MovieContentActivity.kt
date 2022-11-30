package com.example.smartcity6.UI.activity.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.network.MovieContent
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_movie_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_content)

        supportActionBar?.hide()

        val id = intent.getIntExtra("movie_id",0)

        ServiceCreate.smartCityCrete.getMovieContent(id).enqueue(object :Callback<MovieContent>{
            override fun onFailure(p0: Call<MovieContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<MovieContent>, p1: Response<MovieContent>) {
                val body = p1.body()
                if (body!= null){
                    Glide.with(this@MovieContentActivity).load(ServiceCreate.url + body.data.cover).into(movie_image2)
                    movie_title2.text = body.data.name
                    pingfen.text = body.data.score.toString()
                    shijian.text = body.data.duration.toString()
                    renshu.text = body.data.likeNum.toString()
                }
            }

        })

        go_movie.setOnClickListener {
            startActivity(Intent(this,MovieActivity::class.java))
            finish()
        }

    }
}