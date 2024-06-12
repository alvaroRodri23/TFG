package com.example.proyecto_si

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AnimeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton
    private lateinit var botonforo: ImageButton
    private val trailersMap = mapOf(
        R.id.imageAnime1 to "https://www.youtube.com/watch?v=a70_eOnIS3o&ab_channel=CrunchyrollenEspa%C3%B1ol",
        R.id.imageAnime2 to "https://www.youtube.com/watch?v=bXgip0F6qdc&ab_channel=Crunchyroll",
        R.id.imageAnime3 to "https://www.youtube.com/watch?v=FRn6xXXF-7s&ab_channel=CrunchyrollenEspa%C3%B1ol",
        R.id.imageAnime4 to "https://www.youtube.com/watch?v=yQJM963be_I&ab_channel=IGN",
        R.id.imageAnime5 to "https://www.youtube.com/watch?v=TiIZ2NZxjMI&ab_channel=CrunchyrollenEspa%C3%B1ol",
        R.id.imageAnime6 to "https://www.youtube.com/watch?v=PrgxJ1_sUcs&ab_channel=Netflix",
        R.id.imageAnime7 to "https://www.youtube.com/watch?v=Yl_f0sYMaGk&ab_channel=Crunchyroll",
        R.id.imageAnime8 to "https://www.youtube.com/watch?v=mxUEUrj8Ezw&ab_channel=FandangoLatam",
        R.id.imageAnime9 to "https://www.youtube.com/watch?v=2rPPppw6Bhk&ab_channel=CrunchyrollenEspa%C3%B1ol",
        R.id.imageAnime10 to "https://www.youtube.com/watch?v=-AwLMRgcEoA&ab_channel=Cin%C3%A9polis"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anime_activity)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
        botonforo = findViewById(R.id.botonforo)


        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

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

        val images = listOf(
            R.id.imageAnime1,
            R.id.imageAnime2,
            R.id.imageAnime3,
            R.id.imageAnime4,
            R.id.imageAnime5,
            R.id.imageAnime6,
            R.id.imageAnime7,
            R.id.imageAnime8,
            R.id.imageAnime9,
            R.id.imageAnime10
        )

        images.forEach { imageId ->
            val imageView = findViewById<ImageView>(imageId)
            imageView.setOnClickListener {
               val title = getTitleForImage(imageId)
                val imageResource = getImageResourceForImage(imageId)
                val trailer = getTrailerForImage(imageId)
                val intent = Intent(this, infostream::class.java).apply {
                    putExtra("title", title)
                   putExtra("imageResource", imageResource)
                    putExtra("trailer", trailer)
                }
                startActivity(intent)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_one -> {
                val intent = Intent(this, principal2::class.java)
                startActivity(intent)
            }
            R.id.nav_item_two -> {
                val intent = Intent(this, AnalyticsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_six -> {
                val intent = Intent(this, PelisActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_seven -> {
                val intent = Intent(this, SeriesActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_ocho -> {
                val intent = Intent(this, AnimeActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_nueve -> {
                // Código para cerrar sesión
                showToast("Cerrar sesión")
                // Lógica para cerrar sesión aquí
            }
            else -> showToast("Unknown item selected")
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

   private fun getTitleForImage(imageId: Int): String {
        return when (imageId) {
            R.id.imageAnime1 -> "Jujutsu Kaisen Season 2"
            R.id.imageAnime2 -> "Dr.Stone New World"
            R.id.imageAnime3 -> "Attack on titan"
            R.id.imageAnime4 -> "Dragon Ball Super"
            R.id.imageAnime5 -> "Black Clover"
            R.id.imageAnime6 -> "Black Clover Sword of the Wizard King"
            R.id.imageAnime7 -> "Jujutsu Kaisen 0"
            R.id.imageAnime8 -> "Dragon ball super broly"
            R.id.imageAnime9 -> "Suzume"
            R.id.imageAnime10 -> "Guardianes de la noche"
            else -> "Título genérico"
        }
    }

    private fun getImageResourceForImage(imageId: Int): Int {
        return when (imageId) {
            R.id.imageAnime1 -> R.drawable.jjks2
            R.id.imageAnime2 -> R.drawable.drstone
            R.id.imageAnime3 -> R.drawable.shingeki
            R.id.imageAnime4 -> R.drawable.db
            R.id.imageAnime5 -> R.drawable.bc
            R.id.imageAnime6 -> R.drawable.bcfilm
            R.id.imageAnime7 -> R.drawable.jjk0
            R.id.imageAnime8 -> R.drawable.dbsbroly2
            R.id.imageAnime9 -> R.drawable.suzume
            R.id.imageAnime10 -> R.drawable.tren
            else -> R.drawable.animes
        }
    }



    private fun getTrailerForImage(imageId: Int): String {
        return trailersMap[imageId] ?: "Trailer genérico"
    }
}
