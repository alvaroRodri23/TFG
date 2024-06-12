package com.example.proyecto_si

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
private lateinit var botonPerfil: ImageButton
private lateinit var botonHome: ImageButton
private lateinit var botonAnaliticas: ImageButton
private lateinit var botonforo: ImageButton

class AnalyticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.analytics_activity)

        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
        botonforo = findViewById(R.id.botonforo)


        // Configurar clics de botón
        botonPerfil.setOnClickListener {
            val intent = Intent(this, perfilactivity::class.java)
            startActivity(intent)
        }

        botonHome.setOnClickListener {
            val intent = Intent(this, principal2::class.java)
            startActivity(intent)
        }

        botonAnaliticas.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }
        botonforo.setOnClickListener {
            val intent = Intent(this, CommentsActivity::class.java)
            startActivity(intent)
        }
    }
}
