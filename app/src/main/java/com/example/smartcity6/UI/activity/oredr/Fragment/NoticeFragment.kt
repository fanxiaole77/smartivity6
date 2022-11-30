package com.example.smartcity6.UI.activity.oredr.Fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity6.R
import com.example.smartcity6.network.Metro
import com.example.smartcity6.network.ServiceCreate
import kotlinx.android.synthetic.main.fragment_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NoticeFragment : Fragment() {

    /**
     * 须知
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ServiceCreate.smartCityCrete.getMetro().enqueue(object :Callback<Metro>{
            override fun onFailure(p0: Call<Metro>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<Metro>, p1: Response<Metro>) {
               val body = p1.body()
                if (body != null){
                    tv_notice.text = Html.fromHtml(body.data.content)
                }
            }

        })
    }


}