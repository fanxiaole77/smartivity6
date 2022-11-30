package com.example.smartcity6.UI.activity.volunteer.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity6.R
import com.example.smartcity6.UI.activity.volunteer.VoContentActivity
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.ActivityList
import com.example.smartcity6.network.Message
import com.example.smartcity6.network.RegisterActivity
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_volunteer_activities.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * 志愿活动
 */

class VolunteerActivitiesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer_activities, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ServiceCreate.smartCityCrete.getActivityList(null).enqueue(object :Callback<ActivityList>{
            override fun onFailure(p0: Call<ActivityList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<ActivityList>, p1: Response<ActivityList>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_vo_list,body.rows,BN::class.java)
                    rv_vo_list.layoutManager = LinearLayoutManager(this@VolunteerActivitiesFragment.requireActivity())
                    rv_vo_list.adapter = adapter
                }
            }

        })

    }
}

class BN(view: View):ItemAdapter.MyViewHolder(view){
    val title:TextView = view.findViewById(R.id.vo_list_title)
    val danwei:TextView = view.findViewById(R.id.vo_list_danwei)
    val time:TextView = view.findViewById(R.id.vo_list_time)
    val baoming:TextView = view.findViewById(R.id.baoming)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        title.text = (data[position] as ActivityList.Row).title
        danwei.text = (data[position] as ActivityList.Row).undertaker
        time.text = (data[position] as ActivityList.Row).startAt

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,VoContentActivity::class.java).apply {
                putExtra("vo_id",(data[position] as ActivityList.Row).id)
            })
        }

        baoming.setOnClickListener {
            ServiceCreate.smartCityCrete.postRegisterActivity(RegisterActivity((data[position] as ActivityList.Row).id,true)).enqueue(object :Callback<Message>{
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