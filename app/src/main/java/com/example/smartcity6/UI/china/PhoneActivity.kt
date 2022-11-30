package com.example.smartcity6.UI.china

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.smartcity6.R
import kotlinx.android.synthetic.main.activity_phone.*
import java.io.File
import java.io.IOException
import java.lang.Exception

class PhoneActivity : AppCompatActivity() {

    companion object{
        val TAKE_PHOTO = 1
        val CHOOSE_PHOTO = 2
    }

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        btn_phone.setOnClickListener {
            val outputImage = File(externalCacheDir,"phone.jpg")
            try {
                if (outputImage.exists()){
                    outputImage.delete()
                }
                outputImage.createNewFile()
            }catch (e:IOException){
                e.printStackTrace()
            }

            if (Build.VERSION.SDK_INT < 24){  //SDK版本
                imageUri = Uri.fromFile(outputImage)
            }else{
                imageUri = FileProvider.getUriForFile(
                    this,
                    "com.ywjh.cameraalbumtest.fileprovider", //唯一标识
                    outputImage
                )
            }

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,PhoneActivity.TAKE_PHOTO)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            TAKE_PHOTO -> if (resultCode == Activity.RESULT_OK){
                try {
                    val btimap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
                    image_phone.setImageBitmap(btimap)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }
}