package com.tpvs

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener {

    var txtLatitud: EditText? = null
    var txtLongitud:EditText? = null
    var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta_main)

        txtLatitud = findViewById(R.id.txtLatitud)
        txtLongitud = findViewById(R.id.txtLongitud)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            // código para manejar el mapa de Google
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
//        txtLatitud?.setText(latLng.latitude.toString())
//        txtLongitud?.setText(latLng.longitude.toString())
//
//        mMap!!.clear()
//        val mexico = LatLng(latLng.latitude, latLng.longitude)
//        mMap!!.addMarker(MarkerOptions().position(mexico).title(""))
//        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(mexico))
    }
}