package com.example.smartcity6.UI.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Criteria
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.smartcity6.MainActivity
import com.example.smartcity6.R
import com.example.smartcity6.UI.action.LoginActivity
import com.example.smartcity6.extension.sharedPreferences
import com.example.smartcity6.extension.showToast
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_laucnch.*
import java.util.jar.Manifest

open class LaucnchActivity : AppCompatActivity(),View.OnClickListener {

    private val array = arrayListOf(
        R.drawable.launch01,
        R.drawable.launch02,
        R.drawable.launch03,
        R.drawable.launch04,
        R.drawable.launch05
    )
    private var launch:Banner<Int,BannerImageAdapter<Int>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laucnch)

        launch = findViewById(R.id.lacunch_Banner)

        btn_network.setOnClickListener(this)
        go_home.setOnClickListener(this)

        launch?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(this@LaucnchActivity).load(array[p2]).into(p0!!.imageView)
                }

            },false)
            isAutoLoop(false)

            addOnPageChangeListener(object :OnPageChangeListener{
                override fun onPageScrollStateChanged(p0: Int) {
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                    if (p0 == array.size -1){
                        btn_network.visibility = View.VISIBLE
                        go_home.visibility = View.VISIBLE
                    }else{
                        btn_network.visibility = View.GONE
                        go_home.visibility = View.GONE

                    }
                }

                override fun onPageSelected(p0: Int) {
                }

            })
            indicator = CircleIndicator(this@LaucnchActivity)
            setIndicatorSelectedColor(Color.WHITE)
            setIndicatorWidth(20,30)

        }

    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.go_home -> {
                val ip = sharedPreferences.getString("ip","")
                val dk = sharedPreferences.getString("dk","")
                if (TextUtils.isEmpty(ip) && TextUtils.isEmpty(dk)){
                    "请设置网络服务".showToast()
                }else{
                    val token = sharedPreferences.getString("token","")
                    if (TextUtils.isEmpty(token)){
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                }
            }
            R.id.btn_network -> {
                IpActivity(this).apply {
                    showAtLocation(v,Gravity.BOTTOM,width/2,0)

                }
            }
        }

    }


}