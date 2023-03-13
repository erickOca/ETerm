package com.tpvs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val returnIniciarS = findViewById<TextView>(R.id.retIniciarS)

        returnIniciarS.setOnClickListener {val intent =Intent(this,SignInActivity::class.java)
        startActivity(intent)}
    }
}