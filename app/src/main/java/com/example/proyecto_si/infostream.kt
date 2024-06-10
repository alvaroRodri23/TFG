package com.example.proyecto_si

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class infostream : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infostream)

        // Obtener los extras del Intent
        val title = intent.getStringExtra("title")
        val imageResource = intent.getIntExtra("imageResource", -1)
        val trailer = intent.getStringExtra("trailer")

        // Obtener las vistas
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val trailerTextView: TextView = findViewById(R.id.trailerTextView)

        // Asignar los valores a las vistas
        titleTextView.text = title
        if (imageResource != -1) {
            imageView.setImageResource(imageResource)
        }
        trailerTextView.text = trailer ?: "No trailer available"
    }
}
