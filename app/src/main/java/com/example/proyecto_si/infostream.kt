package com.example.proyecto_si

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class infostream : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var rootRef: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var genreTextView: TextView
    private lateinit var directorTextView: TextView
    private lateinit var plotTextView: TextView
    private lateinit var actorsTextView: TextView
    private lateinit var yearTextView: TextView
    private lateinit var releasedTextView: TextView
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton
    private lateinit var botonforo: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infostream)


        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
        botonforo = findViewById(R.id.botonforo)


        // Inicializar Firebase
        database = FirebaseDatabase.getInstance("https://proyectologin-c9bf6-default-rtdb.firebaseio.com/")
        rootRef = database.reference

        // Inicializar vistas
        genreTextView = findViewById(R.id.genreTextView)
        directorTextView = findViewById(R.id.directorTextView)
        plotTextView = findViewById(R.id.plotTextView)
        actorsTextView = findViewById(R.id.actorsTextView)
        yearTextView = findViewById(R.id.yearTextView)
        releasedTextView = findViewById(R.id.releasedTextView)


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




        // Obtener los extras del Intent
        val title = intent.getStringExtra("title")
        val imageResource = intent.getIntExtra("imageResource", -1)
        val trailer = intent.getStringExtra("trailer")

        // Obtener las vistas
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val imageView: ImageView = findViewById(R.id.imageView)
     //   val trailerTextView: TextView = findViewById(R.id.trailerTextView)

        // Asignar los valores a las vistas
        titleTextView.text = title
        if (imageResource != -1) {
            imageView.setImageResource(imageResource)
        }
      //  trailerTextView.text = trailer ?: "No trailer available"

        // Obtener la información de Firebase
        if (title != null) {
            getMovieInfoFromFirebase(title)
            getAnimeInfoFromFirebase(title)
            getSeriesInfoFromFirebase(title)
        }
    }

    private fun getMovieInfoFromFirebase(title: String) {
        rootRef.child("Data_streams").child("movies").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val movieTitle = ds.child("Title").value.toString()
                    if (movieTitle == title) {
                        val genre = ds.child("Genre").value.toString()
                        val director = ds.child("Director").value.toString()
                        val plot = ds.child("Plot").value.toString()
                        val actors = ds.child("Actors").value.toString()
                        val year = ds.child("Year").value.toString()
                        val released = ds.child("Released").value.toString()

                        Log.w(TAG, "Género: $genre")
                        Log.w(TAG, "Director: $director")
                        Log.w(TAG, "Plot: $plot")
                        Log.w(TAG, "Actors: $actors")
                        Log.w(TAG, "Year: $year")
                        Log.w(TAG, "Released: $released")

                        if (genre != "N/A") {
                            genreTextView.text = "Género: $genre"
                        } else {
                            genreTextView.text = "Género: Sin información"
                        }

                        if (director != "N/A") {
                            directorTextView.text = "Director: $director"
                        } else {
                            directorTextView.text = "Director: Sin información"
                        }

                        if (plot != "N/A") {
                            plotTextView.text = "Argumento: $plot"
                        } else {
                            plotTextView.text = "Argumento: Sin información"
                        }

                        if (actors != "N/A") {
                            actorsTextView.text = "Actores: $actors"
                        } else {
                            actorsTextView.text = "Actores: Sin información"
                        }

                        if (year != "N/A") {
                            yearTextView.text = "Año: $year"
                        } else {
                            yearTextView.text = "Año: Sin información"
                        }

                        if (released != "N/A") {
                            releasedTextView.text = "Fecha de lanzamiento: $released"
                        } else {
                            releasedTextView.text = "Fecha de lanzamiento: Sin información"
                        }



                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Error al obtener información de la película", error.toException())
            }
        })
    }

    private fun getAnimeInfoFromFirebase(title: String) {
        rootRef.child("Data_streams").child("anime").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val animeTitle = ds.child("Title").value.toString()
                    if (animeTitle == title) {
                        val genre = ds.child("Genre").value.toString()
                        val director = ds.child("Director").value.toString()
                        val plot = ds.child("Plot").value.toString()
                        val actors = ds.child("Actors").value.toString()
                        val year = ds.child("Year").value.toString()
                        val released = ds.child("Released").value.toString()

                        Log.w(TAG, "Género: $genre")
                        Log.w(TAG, "Director: $director")
                        Log.w(TAG, "Plot: $plot")
                        Log.w(TAG, "Actors: $actors")
                        Log.w(TAG, "Year: $year")
                        Log.w(TAG, "Released: $released")

                        if (genre != "N/A") {
                            genreTextView.text = "Género: $genre"
                        } else {
                            genreTextView.text = "Género: Sin información"
                        }

                        if (director != "N/A") {
                            directorTextView.text = "Director: $director"
                        } else {
                            directorTextView.text = "Director: Sin información"
                        }

                        if (plot != "N/A") {
                            plotTextView.text = "Argumento: $plot"
                        } else {
                            plotTextView.text = "Argumento: Sin información"
                        }

                        if (actors != "N/A") {
                            actorsTextView.text = "Actores: $actors"
                        } else {
                            actorsTextView.text = "Actores: Sin información"
                        }

                        if (year != "N/A") {
                            yearTextView.text = "Año: $year"
                        } else {
                            yearTextView.text = "Año: Sin información"
                        }

                        if (released != "N/A") {
                            releasedTextView.text = "Fecha de lanzamiento: $released"
                        } else {
                            releasedTextView.text = "Fecha de lanzamiento: Sin información"
                        }

                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Error al obtener información del anime", error.toException())
            }
        })
    }

    private fun getSeriesInfoFromFirebase(title: String) {
        rootRef.child("Data_streams").child("series").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val seriesTitle = ds.child("Title").value.toString()
                    if (seriesTitle == title) {
                        val genre = ds.child("Genre").value.toString()
                        val director = ds.child("Director").value.toString()
                        val plot = ds.child("Plot").value.toString()
                        val actors = ds.child("Actors").value.toString()
                        val year = ds.child("Year").value.toString()
                        val released = ds.child("Released").value.toString()

                        Log.w(TAG, "Género: $genre")
                        Log.w(TAG, "Director: $director")
                        Log.w(TAG, "Plot: $plot")
                        Log.w(TAG, "Actors: $actors")
                        Log.w(TAG, "Year: $year")
                        Log.w(TAG, "Released: $released")

                        if (genre != "N/A") {
                            genreTextView.text = "Género: $genre"
                        } else {
                            genreTextView.text = "Género: Sin información"
                        }

                        if (director != "N/A") {
                            directorTextView.text = "Director: $director"
                        } else {
                            directorTextView.text = "Director: Sin información"
                        }

                        if (plot != "N/A") {
                            plotTextView.text = "Argumento: $plot"
                        } else {
                            plotTextView.text = "Argumento: Sin información"
                        }

                        if (actors != "N/A") {
                            actorsTextView.text = "Actores: $actors"
                        } else {
                            actorsTextView.text = "Actores: Sin información"
                        }

                        if (year != "N/A") {
                            yearTextView.text = "Año: $year"
                        } else {
                            yearTextView.text = "Año: Sin información"
                        }

                        if (released != "N/A") {
                            releasedTextView.text = "Fecha de lanzamiento: $released"
                        } else {
                            releasedTextView.text = "Fecha de lanzamiento: Sin información"
                        }


                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Error al obtener información de la serie", error.toException())
            }
        })
    }


}
