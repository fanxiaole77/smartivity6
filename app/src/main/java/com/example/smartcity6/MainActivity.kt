package com.example.smartcity6

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.smartcity6.UI.`fun`.FunFragment
import com.example.smartcity6.UI.china.ChinaFragment
import com.example.smartcity6.UI.home.HomeFragment
import com.example.smartcity6.UI.me.MeFragment
import com.example.smartcity6.UI.news.NewsFragment
import com.example.smartcity6.extension.sharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bottom1: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom1 = findViewById(R.id.bottom)

        supportActionBar?.hide()
        SmartCityApplication.TOKEN = sharedPreferences.getString("token","").toString()
        setEvent()
        loadFragment(HomeFragment())
        bottom.setOnNavigationItemSelectedListener {
            when(it?.itemId){
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_fun -> {
                    loadFragment(FunFragment())
                }
                R.id.nav_china -> {
                    loadFragment(ChinaFragment())
                }
                R.id.nav_news -> {
                    loadFragment(NewsFragment())
                }
                R.id.nav_me -> {
                    loadFragment(MeFragment())
                }
            }
            true
        }

    }

    private fun setEvent(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
    }

    private fun loadFragment(fm:Fragment){
        val aa = supportFragmentManager.beginTransaction()
        aa.replace(R.id.fragment,fm)
        aa.commit()
    }
}