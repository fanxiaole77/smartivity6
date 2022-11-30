package com.example.smartcity6.UI.activity.volunteer.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.ServiceCreate
import com.example.smartcity6.network.VolunteerBanner
import com.example.smartcity6.network.VolunteerNews
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.fragment_volunteer_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *志愿活动首页
 *
 */

class VolunteerHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ServiceCreate.smartCityCrete.getVolunteerBanner().enqueue(object :Callback<VolunteerBanner>{
            override fun onFailure(p0: Call<VolunteerBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<VolunteerBanner>, p1: Response<VolunteerBanner>) {
                val body = p1.body()
                if (body != null){
                    vo_banner.adapter = object :BannerImageAdapter<VolunteerBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: VolunteerBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@VolunteerHomeFragment.requireActivity()).load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }

                    }
                }
            }

        })

        ServiceCreate.smartCityCrete.getVolunteerNews().enqueue(object :Callback<VolunteerNews>{
            override fun onFailure(p0: Call<VolunteerNews>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<VolunteerNews>, p1: Response<VolunteerNews>) {
               val body = p1.body()
                if (body != null){
                    Log.d("vo","${body.rows}")
                    val adapter = ItemAdapter(R.layout.item_vo_news,body.rows,NN::class.java)
                    rv_vo_news.layoutManager = LinearLayoutManager(this@VolunteerHomeFragment.requireActivity())
                    rv_vo_news.adapter = adapter
                }
            }

        })

    }



}

class NN(view: View):ItemAdapter.MyViewHolder(view){
    val image:ImageView = view.findViewById(R.id.vo_image)
    val title:TextView = view.findViewById(R.id.vo_title)
    val content:TextView = view.findViewById(R.id.vo_content)
    val time:TextView = view.findViewById(R.id.vo_time)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as VolunteerNews.Row).imgUrl).into(image)
        time.text = (data[position] as VolunteerNews.Row).title
        content.text = Html.fromHtml((data[position] as VolunteerNews.Row).summary)
        time.text = (data[position] as VolunteerNews.Row).createTime
    }
}