package com.example.proyecto_si

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Explorer_genres : AppCompatActivity() {

    private lateinit var listViewGeneros: ListView
    private lateinit var listViewTitulos: ListView
    private lateinit var textViewTitulos: TextView
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_generos)

        listViewGeneros = findViewById(R.id.listViewGeneros)
        listViewTitulos = findViewById(R.id.listViewTitulos)
        textViewTitulos = findViewById(R.id.textViewTitulos)

        databaseReference = FirebaseDatabase.getInstance().getReference("Data_streams")

        mostrarTodosLosTitulos()

        // Configurar ListView de géneros
        val generos = arrayOf("Action", "Comedy", "Fantasy", "Drama", "Thriller", "Sci-Fi")
        val generosAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generos)
        listViewGeneros.adapter = generosAdapter

        // Manejar clics en géneros
        listViewGeneros.setOnItemClickListener { _, _, position, _ ->
            val generoSeleccionado = generos[position]
            mostrarTitulosPorGenero(generoSeleccionado)
        }
    }

    private fun mostrarTitulosPorGenero(genero: String) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val titulos = mutableListOf<String>()
                for (snapshot in dataSnapshot.children) {
                    val genreString = snapshot.child("Genre").getValue(String::class.java)
                    val genres = genreString?.split(", ") ?: emptyList()
                    if (genres.contains(genero)) {
                        val titulo = snapshot.child("Title").getValue(String::class.java)
                        titulo?.let { titulos.add(it) }
                    }
                }
                val titulosAdapter = ArrayAdapter(this@Explorer_genres, android.R.layout.simple_list_item_1, titulos)
                listViewTitulos.adapter = titulosAdapter
                listViewTitulos.visibility = View.VISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de consulta si es necesario
            }
        })
    }

    private fun mostrarTodosLosTitulos() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val titulos = mutableListOf<String>()
                for (snapshot in dataSnapshot.children) {
                    val titulo = snapshot.child("Title").getValue(String::class.java)
                    titulo?.let { titulos.add(it) }
                }
                textViewTitulos.text = "Títulos:\n${titulos.joinToString("\n")}"
                textViewTitulos.visibility = View.VISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de consulta si es necesario
            }
        })
    }
}
