/*package com.example.proyecto_si
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto_si.R

class infopelis : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infopelis)

        // Obtener datos del Intent
        val titulo = intent.getStringExtra("titulo")
        val imagenResId = intent.getIntExtra("imagen", 0) // 0 es el valor predeterminado si no se encuentra la clave
        val sinopsis = intent.getStringExtra("sinopsis")
        val URLvideo = intent.getStringExtra("URL")

        // Establecer datos en la UI
        findViewById<TextView>(R.id.Tituloinfo).text = titulo
        findViewById<ImageView>(R.id.fotoinfo).setImageResource(imagenResId)
        findViewById<TextView>(R.id.Sinopsis).text = sinopsis

        // Configurar un OnClickListener para abrir el video en YouTube cuando se hace clic en el botón "trailer"
        val trailerButton = findViewById<Button>(R.id.Trailer)
        trailerButton.setOnClickListener {
            // Intent para abrir la URL en la aplicación de YouTube
            val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + Uri.parse(URLvideo).getQueryParameter("v")))
            // Si la aplicación de YouTube no está instalada, abrir la URL en el navegador web
            if (youtubeIntent.resolveActivity(packageManager) != null) {
                startActivity(youtubeIntent)
            } else {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(URLvideo))
                startActivity(webIntent)
            }
        }
        val backButton = findViewById<Button>(R.id.botonatrasinfo)
        backButton.setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }
}


 */