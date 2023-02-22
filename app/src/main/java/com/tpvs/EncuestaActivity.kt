package com.tpvs

import android.app.Activity
import android.graphics.Bitmap
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.annotation.RequiresApi
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts

class EncuestaActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        val btnCam1 = findViewById<Button>(R.id.btnCam1)


        btnCam1.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageBitmap(imageBitmap)
            }
        }
}