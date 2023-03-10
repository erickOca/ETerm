package com.tpvs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class EncuestaActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)



        val btnCam1 = findViewById<Button>(R.id.btnCam1)
        val btnCam2 = findViewById<Button>(R.id.btnCam2)
        val btnCam3 = findViewById<Button>(R.id.btnCam3)


        btnCam1.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            btnCam2.setOnClickListener {
                startForResult2.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                btnCam3.setOnClickListener {
                    startForResult3.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                }
            }
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageBitmap(imageBitmap)
            }
        }
    private val startForResult2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = findViewById<ImageView>(R.id.imageView2)
                imageView.setImageBitmap(imageBitmap)
            }
        }
    private val startForResult3 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = findViewById<ImageView>(R.id.imageView3)
                imageView.setImageBitmap(imageBitmap)
            }
        }




}