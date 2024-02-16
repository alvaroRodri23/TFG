package com.example.proyecto_si

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class perfilactivity : AppCompatActivity() {

    private lateinit var imageViewPerfil: ImageView
    private lateinit var recyclerViewProfileOptions: RecyclerView
    private val photos = listOf(R.drawable.senkupfp, R.drawable.astapfp, R.drawable.itadoripfp,R.drawable.gokupfp, R.drawable.yutapfp,R.drawable.suzumepfp, R.drawable.vegetapfp,R.drawable.makipfp, R.drawable.erenpfp,R.drawable.tokitopfp)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        findViewById<Button>(R.id.botonprueba2).setOnClickListener {
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }


        imageViewPerfil = findViewById(R.id.imageButton3)
        recyclerViewProfileOptions = findViewById(R.id.recyclerViewProfileOptions)

        recyclerViewProfileOptions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Inicializar y establecer el adaptador
        val adapter = ProfileOptionsAdapter(photos) { photo ->
            // Cuando el usuario elige una foto, se establece como foto de perfil
            imageViewPerfil.setImageResource(photo)

            // Guardar la foto de perfil utilizando SharedPreferences
            val sharedPreferences = getSharedPreferences("Perfil", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("photoId", photo).apply()

            // Indicar que se seleccionó una foto de perfil
            setResult(Activity.RESULT_OK)
        }
        recyclerViewProfileOptions.adapter = adapter


        val sharedPreferences = getSharedPreferences("Perfil", Context.MODE_PRIVATE)
        val savedPhotoId = sharedPreferences.getInt("photoId", -1)

        if (savedPhotoId != -1) {
            // Si hay una imagen de perfil guardada, establecerla en el ImageView
            imageViewPerfil.setImageResource(savedPhotoId)
        }

    }


}
