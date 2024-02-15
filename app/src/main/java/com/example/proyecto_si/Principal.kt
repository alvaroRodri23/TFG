package com.example.proyecto_si
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class Principal : AppCompatActivity() {

    private lateinit var seriesButton: ImageButton
    private lateinit var moviesButton: ImageButton
    private lateinit var seriesScrollView: ScrollView
    private lateinit var moviesScrollView: ScrollView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        seriesButton = findViewById(R.id.botonseries)
        moviesButton = findViewById(R.id.botonpelis)
        seriesScrollView = findViewById(R.id.scrollviewseries)
        moviesScrollView = findViewById(R.id.scrollviewpeliculas)

        seriesButton.setOnClickListener {
            // Mostrar ScrollView de series y ocultar ScrollView de películas
            seriesScrollView.visibility = View.VISIBLE
            moviesScrollView.visibility = View.GONE
        }

        moviesButton.setOnClickListener {
            // Mostrar ScrollView de películas y ocultar ScrollView de series
            seriesScrollView.visibility = View.GONE
            moviesScrollView.visibility = View.VISIBLE
        }

        // Configurar OnClickListener para las imágenes de las series
        findViewById<ImageView>(R.id.imageViewscroll1).setOnClickListener {
            // Aquí puedes pasar información de la serie al Intent
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Jujutsu Kaisen Season 2")
            intent.putExtra("imagen", R.drawable.jjks2)
            intent.putExtra("sinopsis", "Sinopsis de Jujutsu Kaisen Season 2")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll2).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Dr.Stone New World")
            intent.putExtra("imagen", R.drawable.drstone)
            intent.putExtra("sinopsis", "Sinopsis de Dr.Stone New World")
            startActivity(intent)
        }

        // Agrega configuraciones para el resto de las imágenes aquí
    }
}



