package com.tpvs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class EncuestaMainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener {

    var txtLatitud: EditText? = null
    var txtLongitud: EditText? = null
    var mMap: GoogleMap? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta_main)

        val btnCam1 = findViewById<Button>(R.id.btnCam1)
        val btnCam2 = findViewById<Button>(R.id.btnCam2)
        val btnCam3 = findViewById<Button>(R.id.btnCam3)
        txtLatitud = findViewById(R.id.txtLatitud)
        txtLongitud = findViewById(R.id.txtLongitud)
        //mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        //camara
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.setOnMapClickListener(this)
        mMap!!.setOnMapLongClickListener(this)

        val mexico = LatLng(19.8077463, -99.4077038)
        mMap!!.addMarker(MarkerOptions().position(mexico).title("México"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(mexico))
    }

    override fun onMapClick(latLng: LatLng) {
        txtLatitud?.setText(latLng.latitude.toString())
        txtLongitud?.setText(latLng.longitude.toString())

        mMap!!.clear()
        val mexico = LatLng(latLng.latitude, latLng.longitude)
        mMap!!.addMarker(MarkerOptions().position(mexico).title(""))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(mexico))
    }

    override fun onMapLongClick(latLng: LatLng) {
        txtLatitud?.setText(latLng.latitude.toString())
        txtLongitud?.setText(latLng.longitude.toString())

        mMap!!.clear()
        val mexico = LatLng(latLng.latitude, latLng.longitude)
        mMap!!.addMarker(MarkerOptions().position(mexico).title(""))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(mexico))
    }
}

