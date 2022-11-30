package com.example.smartcity6.UI.activity.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.MovieBanner
import com.example.smartcity6.network.MovieList
import com.example.smartcity6.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_mvoie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvoie)

        supportActionBar?.hide()

        ee.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        ee.setOnClickListener { finish() }

        ServiceCreate.smartCityCrete.getMovieBanner().enqueue(object :Callback<MovieBanner>{
            override fun onFailure(p0: Call<MovieBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<MovieBanner>, p1: Response<MovieBanner>) {
                val body = p1.body()
                if (body != null){
                    movie_banner.adapter = object :BannerImageAdapter<MovieBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: MovieBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@MovieActivity).load(ServiceCreate.url + p1!!.advImg).into(p0!!.imageView)
                        }

                    }
                }
            }

        })

        se_movie.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                ServiceCreate.smartCityCrete.getMovieList(newText.toString()).enqueue(object :Callback<MovieList>{
                    override fun onFailure(p0: Call<MovieList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<MovieList>, p1: Response<MovieList>) {
                        val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_movie,body.rows,YY::class.java)
                            rv_movie.layoutManager = LinearLayoutManager(this@MovieActivity)
                            rv_movie.adapter = adapter
                        }
                    }

                })
               return false
            }

        })
        var mSize = 5


        ServiceCreate.smartCityCrete.getMovieList(null).enqueue(object :Callback<MovieList>{
            override fun onFailure(p0: Call<MovieList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<MovieList>, p1: Response<MovieList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_movie,body.rows,YY::class.java,mSize)
                    rv_movie.layoutManager = LinearLayoutManager(this@MovieActivity)
                    rv_movie.adapter = adapter

                    btn_duo.setOnClickListener {
                        mSize += 5
                        if (mSize < body.rows.size){
                            val adapter = ItemAdapter(R.layout.item_movie,body.rows,YY::class.java,mSize)
                            rv_movie.adapter = adapter
                        }else{
                            val adapter = ItemAdapter(R.layout.item_movie,body.rows,YY::class.java)
                            rv_movie.adapter = adapter
                        }

                    }
                }
            }
        })


    }
}

class YY(view:View):ItemAdapter.MyViewHolder(view){
    val title:TextView = view.findViewById(R.id.movie_title)
    val time:TextView = view.findViewById(R.id.movie_time)
    val time_l:TextView = view.findViewById(R.id.movie_time_l)
    val image:ImageView = view.findViewById(R.id.movie_image)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as MovieList.Row).cover).into(image)
        title.text = (data[position] as MovieList.Row).name
        time.text = (data[position] as MovieList.Row).playDate
        time_l.text = (data[position] as MovieList.Row).duration.toString()
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,MovieContentActivity::class.java).apply {
                putExtra("movie_id",(data[position] as MovieList.Row).id)
            })
        }
    }
}