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
    private val photos = listOf(R.drawable.senkupfp, R.drawable.astapfp, R.drawable.itadoripfp,R.drawable.gokupfp, R.drawable.yutapfp,R.drawable.suzumepfp, R.drawable.vegetapfp,R.drawable.makipfp, R.drawable.erenpfp,R.drawable.tokitopfp)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        imageViewPerfil = findViewById(R.id.imageButton3)
        recyclerViewProfileOptions = findViewById(R.id.recyclerViewProfileOptions)


        recyclerViewProfileOptions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewProfileOptions.adapter = ProfileOptionsAdapter(photos) { photo ->
            // Cuando el usuario elige una foto,se establece como foto de perfil
            imageViewPerfil.setImageResource(photo)
        }
    }
}
