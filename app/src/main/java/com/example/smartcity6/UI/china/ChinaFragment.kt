package com.example.smartcity6.UI.china

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.ImageUpload
import com.example.smartcity6.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_china.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.jar.Manifest

class ChinaFragment : Fragment() {

    private val array = arrayListOf(
        R.drawable.jg1,
        R.drawable.jg2
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_china, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        china_banner.adapter = object :BannerImageAdapter<Int>(array){
            override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                Glide.with(this@ChinaFragment.requireActivity()).load(array[p2]).into(p0!!.imageView)
            }

        }

        btn_11.setOnClickListener {
            startActivity(Intent(activity,ContentActivity::class.java))
        }
        btn_22.setOnClickListener {
            startActivity(Intent(activity,ContentActivity::class.java))
        }
        btn_33.setOnClickListener {
            startActivity(Intent(activity,ContentActivity::class.java))
        }
        btn_44.setOnClickListener {
            startActivity(Intent(activity,ContentActivity::class.java))
        }
        btn_55.setOnClickListener {
            startActivity(Intent(activity,ContentActivity::class.java))
        }

        im1.setOnClickListener {
            startActivity(Intent(activity,PhActivity::class.java))
        }
        im2.setOnClickListener {
            startActivity(Intent(activity,YYActivity::class.java))
        }

        im3.setOnClickListener {
            startActivity(Intent(activity,JkActivity::class.java))
        }


    }

}

