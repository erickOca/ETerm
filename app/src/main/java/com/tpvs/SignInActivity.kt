package com.tpvs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val buttonIniciarS = findViewById<Button>(R.id.iniciarSesion)
        val registrarse = findViewById<TextView>(R.id.registrate)

        buttonIniciarS.setOnClickListener {
            val intent = Intent(this, EncuestaMainActivity::class.java)
            startActivity(intent)
        }
        registrarse.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}