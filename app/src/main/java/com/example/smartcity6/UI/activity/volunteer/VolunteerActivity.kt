package com.example.smartcity6.UI.activity.volunteer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smartcity6.R
import com.example.smartcity6.UI.activity.volunteer.fragment.MyActivityFragment
import com.example.smartcity6.UI.activity.volunteer.fragment.VolunteerActivitiesFragment
import com.example.smartcity6.UI.activity.volunteer.fragment.VolunteerHomeFragment
import kotlinx.android.synthetic.main.activity_volunteer.*

/**
 * 志愿服务
 */

class VolunteerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer)

        supportActionBar?.hide()

        loadFragment(VolunteerHomeFragment())

        vo_bottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_activity_home -> {
                    loadFragment(VolunteerHomeFragment())
                }

                R.id.nav_activity_activity -> {
                    loadFragment(VolunteerActivitiesFragment())
                }

                R.id.nav_activity_my -> {
                    loadFragment(MyActivityFragment())
                }
            }
            true
        }

    }
    private fun loadFragment(fm:Fragment){
        val aa = supportFragmentManager.beginTransaction()
        aa.replace(R.id.vo_fm,fm)
        aa.commit()
    }
}