package com.example.smartcity6.UI.`fun`

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
import com.example.smartcity6.R
import com.example.smartcity6.UI.activity.bus.BusActivity
import com.example.smartcity6.UI.activity.movie.MovieActivity
import com.example.smartcity6.UI.activity.oredr.OrderActivity
import com.example.smartcity6.UI.activity.volunteer.VolunteerActivity
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.HomeService
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_fun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FunFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ServiceCreate.smartCityCrete.getHomeService().enqueue(object : Callback<HomeService> {
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
                val body = p1.body()
                if (body != null) {
                    val adapter  =ItemAdapter(R.layout.item_servce,body.rows,EE::class.java)
                    rv_fun.layoutManager = GridLayoutManager(this@FunFragment.requireActivity(),5)
                    rv_fun.adapter = adapter
                }
            }

        })

    }

}


class EE(view: View) : ItemAdapter.MyViewHolder(view) {
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HomeService.Row).serviceName
        Glide.with(image).load(ServiceCreate.url + (data[position] as HomeService.Row).imgUrl)
            .into(image)

        when((data[position] as HomeService.Row).serviceName){
            "智慧巴士" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,BusActivity::class.java))
                }
            }
            "智慧交管" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,OrderActivity::class.java))
                }
            }
            "看电影" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,MovieActivity::class.java))
                }
            }
            "志愿服务" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,VolunteerActivity::class.java))
                }
            }
        }

    }
}