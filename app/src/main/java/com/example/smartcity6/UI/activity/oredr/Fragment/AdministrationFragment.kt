package com.example.smartcity6.UI.activity.oredr.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity6.R
import com.example.smartcity6.UI.activity.oredr.AddCarActivity
import com.example.smartcity6.extension.ItemAdapter
import com.example.smartcity6.network.CarList
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_administration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdministrationFragment : Fragment() {

    /**
     * 车辆管理
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_car_add.setOnClickListener {
            startActivity(Intent(activity,AddCarActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        ServiceCreate.smartCityCrete.getCArList().enqueue(object :Callback<CarList>{
            override fun onFailure(p0: Call<CarList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<CarList>, p1: Response<CarList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.itm_car,body.rows,QW::class.java)
                    rv_car.layoutManager = LinearLayoutManager(this@AdministrationFragment.requireActivity())
                    rv_car.adapter = adapter
                }
            }

        })

    }


}

class QW(view: View):ItemAdapter.MyViewHolder(view){
    val chebai:TextView = view.findViewById(R.id.tv_plate)
    val chejia:TextView = view.findViewById(R.id.tv_frame)
    val leixing:TextView = view.findViewById(R.id.tv_type)
    val fonglishu:TextView = view.findViewById(R.id.tv_miles)
    override fun binViewHOlder(data: List<Any?>, position: Int, list: List<Any?>) {
        chebai.text = (data[position]as CarList.Row).plateNo
        chejia.text = (data[position]as CarList.Row).engineNo
        leixing.text = (data[position]as CarList.Row).type
        fonglishu.text = "12"

    }

}