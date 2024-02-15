package com.example.proyecto_si

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class perfilactivity : AppCompatActivity() {

    private lateinit var imageViewPerfil: ImageView
    private lateinit var recyclerViewProfileOptions: RecyclerView
    private val photos = listOf(R.drawable.drstone, R.drawable.bc, R.drawable.jjks2) // Lista de fotos de perfil

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        imageViewPerfil = findViewById(R.id.imageButton3)
        recyclerViewProfileOptions = findViewById(R.id.recyclerViewProfileOptions)

        // Configurar RecyclerView para mostrar las opciones de fotos de perfil
        recyclerViewProfileOptions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProfileOptions.adapter = ProfileOptionsAdapter(photos) { photo ->
            // Cuando el usuario elige una foto, la establecemos como foto de perfil
            imageViewPerfil.setImageResource(photo)
        }
    }
}
