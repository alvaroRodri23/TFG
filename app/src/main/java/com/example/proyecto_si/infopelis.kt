package com.example.proyecto_si

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class infopelis : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infopelis)

        // Obtener datos del Intent
        val titulo = intent.getStringExtra("titulo")
        val imagenResId = intent.getIntExtra("imagen", 0) // 0 es el valor predeterminado si no se encuentra la clave
        val sinopsis = intent.getStringExtra("sinopsis")

        // Establecer datos en la UI

        findViewById<TextView>(R.id.Tituloinfo).text = titulo
        findViewById<ImageView>(R.id.fotoinfo).setImageResource(imagenResId)
        findViewById<TextView>(R.id.Sinopsis).text = sinopsis


    }
}
