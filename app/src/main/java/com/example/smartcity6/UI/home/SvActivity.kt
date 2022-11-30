package com.example.smartcity6.UI.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.NewsList
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_sv_s.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv_s)

        supportActionBar?.hide()

        Sv.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                ServiceCreate.smartCityCrete.getNewsList(newText,null).enqueue(object :Callback<NewsList>{
                    override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                       val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_news,body.rows,CC::class.java)
                            rv_sv.layoutManager = LinearLayoutManager(this@SvActivity)
                            rv_sv.adapter = adapter
                        }
                    }

                })

                return false
            }

        })

    }
}