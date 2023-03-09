package com.tpvs

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class EncuestaMainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener {

    var mMap: GoogleMap? = null
    var txtLatitud: EditText? = null
    var txtLongitud: EditText? = null
    var opcionSeleccionada1 = ""
    var opcionSeleccionada2 = ""
    var opcionSeleccionada3 = ""
    var opcionSeleccionada4 = ""
    private val IMAGE_FILE_NAME = "my_image.jpg"
    private val IMAGE_FILE_NAME2 = "my_image2.jpg"
    private val IMAGE_FILE_NAME3 = "my_image3.jpg"
    private lateinit var imageUri: Uri
    private lateinit var imageUri2: Uri
    private lateinit var imageUri3: Uri

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta_main)

        val btnCam1 = findViewById<Button>(R.id.btnCam1)
        val btnCam2 = findViewById<Button>(R.id.btnCam2)
        val btnCam3 = findViewById<Button>(R.id.btnCam3)
        val radioGroup1 = findViewById<RadioGroup>(R.id.rg1)
        val radioGroup2 = findViewById<RadioGroup>(R.id.rg2)
        val radioGroup3 = findViewById<RadioGroup>(R.id.rg3)
        val radioGroup4 = findViewById<RadioGroup>(R.id.rg4)



        txtLatitud = findViewById(R.id.txtLatitud)
        txtLongitud = findViewById(R.id.txtLongitud)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        radioGroup1.setOnCheckedChangeListener { _, checkedId ->
            val radioButton1 = findViewById<RadioButton>(checkedId)
            opcionSeleccionada1 = radioButton1.getTag().toString()
        }
        radioGroup2.setOnCheckedChangeListener { _, checkedId ->
            val radioButton2 = findViewById<RadioButton>(checkedId)
            opcionSeleccionada2 = radioButton2.getTag().toString()
        }
        radioGroup3.setOnCheckedChangeListener { _, checkedId ->
            val radioButton3 = findViewById<RadioButton>(checkedId)
            opcionSeleccionada3 = radioButton3.getTag().toString()
        }
        radioGroup4.setOnCheckedChangeListener { _, checkedId ->
            val radioButton4 = findViewById<RadioButton>(checkedId)
            opcionSeleccionada4 = radioButton4.getTag().toString()
        }

        btnCam1.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        btnCam2.setOnClickListener {
            startForResult2.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        btnCam3.setOnClickListener {
            startForResult3.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap1 = intent?.extras?.get("data") as Bitmap
                val imageView1 = findViewById<ImageView>(R.id.imageView)
                imageView1.setImageBitmap(imageBitmap1)
                saveImageToGallery(imageBitmap1)
            }
        }
    private val startForResult2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap2 = intent?.extras?.get("data") as Bitmap
                val imageView2 = findViewById<ImageView>(R.id.imageView2)
                imageView2.setImageBitmap(imageBitmap2)
                saveImageToGallery2(imageBitmap2)

            }
        }
    private val startForResult3 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap3 = intent?.extras?.get("data") as Bitmap
                val imageView3 = findViewById<ImageView>(R.id.imageView3)
                imageView3.setImageBitmap(imageBitmap3)
                saveImageToGallery3(imageBitmap3)
            }
        }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, IMAGE_FILE_NAME)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri = uri ?: return

        try {
            resolver.openOutputStream(imageUri).use { outputStream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                    throw IOException("Failed to save bitmap.")
                }
            }
        } catch (e: IOException) {
            resolver.delete(imageUri2, null, null)
            imageUri = Uri.EMPTY
            return
        }
    }

    private fun saveImageToGallery2(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, IMAGE_FILE_NAME2)
            put(MediaStore.Images.Media.MIME_TYPE, "image2/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = contentResolver
        val uri2 = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri2 = uri2 ?: return

        try {
            resolver.openOutputStream(imageUri2).use { outputStream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                    throw IOException("Failed to save bitmap.")
                }
            }
        } catch (e: IOException) {
            resolver.delete(imageUri2, null, null)
            imageUri2 = Uri.EMPTY
            return
        }
    }

    private fun saveImageToGallery3(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, IMAGE_FILE_NAME3)
            put(MediaStore.Images.Media.MIME_TYPE, "image3/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = contentResolver
        val uri3 = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri3 = uri3 ?: return

        try {
            resolver.openOutputStream(imageUri3).use { outputStream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                    throw IOException("Failed to save bitmap.")
                }
            }
        } catch (e: IOException) {
            resolver.delete(imageUri3, null, null)
            imageUri3 = Uri.EMPTY
            return
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

    fun save(view: View) {
        var dbHelper = DbHelper(this)
        var encuesta = Encuesta()
        encuesta.pregunta1 = opcionSeleccionada1
        encuesta.pregunta2 = opcionSeleccionada2
        encuesta.pregunta3 = opcionSeleccionada3
        encuesta.pregunta4 = opcionSeleccionada4
        encuesta.imagen1 = imageUri.toString()
        encuesta.imagen2 = imageUri.toString()
        encuesta.imagen3 = imageUri.toString()
        encuesta.latitud = txtLatitud?.text.toString()
        encuesta.longitud = txtLongitud?.text.toString()
        println("" + encuesta)
        dbHelper.saveEncuesta(encuesta)
    }
}


