package com.example.smartcity6.UI.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity6.MainActivity
import com.example.smartcity6.R
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.*
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sv.setOnClickListener {
            startActivity(Intent(activity,SvActivity::class.java))
        }

        ServiceCreate.smartCityCrete.getHomeBanner().enqueue(object :Callback<HomeBanner>{
            override fun onFailure(p0: Call<HomeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeBanner>, p1: Response<HomeBanner>) {
               val body = p1.body()
                if (body != null){
                    home_banner.adapter = object:BannerImageAdapter<HomeBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HomeBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@HomeFragment.requireActivity()).load(ServiceCreate.url + p1!!.advImg).into(p0!!.imageView)
                        }

                    }
                    home_banner.setOnBannerListener(object:OnBannerListener<HomeBanner.Row>{
                        override fun OnBannerClick(p0: HomeBanner.Row?, p1: Int) {
                            startActivity(Intent(this@HomeFragment.requireActivity(),NewsContentActivity::class.java).apply {
                                putExtra("news_id",p0!!.targetId)
                            })
                        }

                    })
                }
            }

        })

        ServiceCreate.smartCityCrete.getHomeService().enqueue(object :Callback<HomeService>{
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {

            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_servce,body.rows,AA::class.java,10,
                        listOf(activity))
                    rv_service.layoutManager = GridLayoutManager(this@HomeFragment.requireActivity(),5)
                    rv_service.adapter = adapter
                }
            }
        })

        ServiceCreate.smartCityCrete.getHot().enqueue(object :Callback<NewsList>{
            override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_hot,body.rows,BB::class.java,4)
                    rv_hot.layoutManager = GridLayoutManager(this@HomeFragment.requireActivity(),2)
                    rv_hot.adapter = adapter
                }
            }

        })

        ServiceCreate.smartCityCrete.getNewsType().enqueue(object:Callback<NewsType>{
            override fun onFailure(p0: Call<NewsType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsType>, p1: Response<NewsType>) {
                val body = p1.body()
                if (body != null){
                    val adapter = PagerAdapter(this@HomeFragment.requireFragmentManager(),body.data)
                    v1.adapter = adapter
                    t1.setupWithViewPager(v1)
                }
            }

        })

    }

}

class AA(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.service_text)
    val image:ImageView = view.findViewById(R.id.service_image)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HomeService.Row).serviceName
        Glide.with(image).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl).into(image)

        if (position == 9){
            name.text = "全部服务"
            Glide.with(image).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl).into(image)
            val  aa = list[0] as MainActivity
            itemView.setOnClickListener {
                aa.bottom1.selectedItemId = R.id.nav_fun
            }
        }
    }
}

class BB(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.hot_text)
    val image:ImageView = view.findViewById(R.id.hot_image)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as NewsList.Row).title
        Glide.with(image).load(ServiceCreate.url + (data[position] as NewsList.Row).cover).into(image)
    }
}