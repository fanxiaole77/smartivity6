package com.example.smartcity6.UI.activity.oredr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smartcity6.R
import com.example.smartcity6.UI.activity.oredr.Fragment.AdministrationFragment
import com.example.smartcity6.UI.activity.oredr.Fragment.ImmediatelyFragment
import com.example.smartcity6.UI.activity.oredr.Fragment.MyFragment
import com.example.smartcity6.UI.activity.oredr.Fragment.NoticeFragment
import kotlinx.android.synthetic.main.activity_order.*

/**
 * 预约检车
 */

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        supportActionBar?.hide()

        loadFragment(NoticeFragment())

        bottom_order.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_xuzhi -> {
                    loadFragment(NoticeFragment())
                }
                R.id.nav_guanli -> {
                    loadFragment(AdministrationFragment())
                }
                R.id.nav_yuyue -> {
                    loadFragment(ImmediatelyFragment())
                }
                R.id.nav_my_yuyue -> {
                    loadFragment(MyFragment())
                }
            }
            true
        }

    }
    private fun loadFragment(fm:Fragment){
        val aa = supportFragmentManager.beginTransaction()
        aa.replace(R.id.fm_order,fm)
        aa.commit()
    }

}