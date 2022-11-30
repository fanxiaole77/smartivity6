package com.example.smartcity6.UI.me

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.smartcity6.R
import com.example.smartcity6.extension.edit
import com.example.smartcity6.extension.sharedPreferences
import com.example.smartcity6.extension.showToast
import com.example.smartcity6.network.*
import kotlinx.android.synthetic.main.activity_user.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserActivity : AppCompatActivity() {
    lateinit var Avatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        q.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        q.setOnClickListener { finish() }

        supportActionBar?.hide()

        Avatar = findViewById(R.id.avatar)

        xiugai.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


        var sex = "0"
        rg_sex.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.man) {
                sex = "0"
            } else {
                sex = "1"
            }
        }
        ServiceCreate.smartCityCrete.getUserInfo().enqueue(object : Callback<UserInfo> {
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                if (body != null) {
                    val id = body.user.idCard
                    Glide.with(this@UserActivity).load(ServiceCreate.url+"prod-api/"+body.user.avatar).into(avatar)
                    Log.d("user","${body.user.avatar}")
                    et_nick11.setText(body.user.nickName)
                    et_phone11.setText(
                        if (id.length > 7) {
                            id.replaceRange(2, id.length - 4, "******")
                        } else {
                            id
                        }
                    )
                    if (body.user.sex == "1") {
                        wu_man.isChecked = true
                    } else {
                        man.isChecked = true
                    }
                }
            }

        })

        save_user.setOnClickListener {
            val nickname = et_nick11.text.toString()
            val phomne = et_phone11.text.toString()
            val avatar1 = sharedPreferences.getString("avatar","")

            ServiceCreate.smartCityCrete.putUser(User(nickname, phomne, sex,avatar1))
                .enqueue(object : Callback<Message> {
                    override fun onFailure(p0: Call<Message>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                        val body = p1.body()
                        if (body != null) {
                            if (body.code == 200) {
                                body.msg.showToast()
                            } else {
                                body.msg.showToast()
                            }
                        }
                    }

                })
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            data?.data?.let { uri ->
                contentResolver.query(uri, null, null, null, null)?.use {
                   if (it.moveToFirst()){
                       val picturePath = it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
                       uploadFile(picturePath)
                   }
                }
            }
        }
        else super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadFile(path: String) {
        val file = File(path)
        Log.d("file", file.toString())
        Avatar.setImageURI(Uri.fromFile(file))
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val filePart = MultipartBody.Part.createFormData(
            "file",
            "test.jpg",
            requestBody
        )

        ServiceCreate.smartCityCrete.upload(filePart).enqueue(object :Callback<ImageUpload>{
            override fun onFailure(p0: Call<ImageUpload>, p1: Throwable) {
                Log.d("p1",p1.message)
            }

            override fun onResponse(p0: Call<ImageUpload>, p1: Response<ImageUpload>) {
                val body = p1.body()
                if (body != null){
                    if (body.code == 200){
                        sharedPreferences.edit {
                            putString("avatar",body.fileName)
                        }
                        Log.d("avatar",body.msg)
                        body.msg.showToast()
                    }else{
                        body.msg.showToast()
                    }
                }
            }

        })
    }
}