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

class PelisActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton
    private lateinit var botonforo: ImageButton


    private val trailersMap = mapOf(
        R.id.imagepeli1 to "https://www.youtube.com/watch?v=UoSSbmD9vqc&ab_channel=WarnerBros.PicturesEspa%C3%B1a",
        R.id.imagepeli2 to "https://www.youtube.com/watch?v=DEMZSa0esCU&ab_channel=TrailersyEstrenos",
        R.id.imagepeli3 to "https://www.youtube.com/watch?v=5PSNL1qE6VY",
        R.id.imagepeli4 to "https://www.youtube.com/watch?v=dtKMEAXyPkg&ab_channel=RottenTomatoesClassicTrailers",
        R.id.imagepeli5 to "https://www.youtube.com/watch?v=UrIbxk7idYA&ab_channel=WarnerBros.Pictures",
        R.id.imagepeli6 to "https://www.youtube.com/watch?v=DYyMsYgZDJM&ab_channel=TrailersyEstrenos",
        R.id.imagepeli7 to "https://www.youtube.com/watch?v=Xcs4OpUJWrM&ab_channel=StarWarsEspa%C3%B1a",
        R.id.imagepeli8 to "https://www.youtube.com/watch?v=6zItrFSDVSc&ab_channel=20thCenturyStudiosEspa%C3%B1a"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pelis_activity)

        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
        botonforo = findViewById(R.id.botonforo)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

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
            R.id.imagepeli1,
            R.id.imagepeli2,
            R.id.imagepeli3,
            R.id.imagepeli4,
            R.id.imagepeli5,
            R.id.imagepeli6,
            R.id.imagepeli7,
            R.id.imagepeli8
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
            R.id.imagepeli1 -> "Interstellar"
            R.id.imagepeli2 -> "The Wolf of Wall Street"
            R.id.imagepeli3 -> "Avatar"
            R.id.imagepeli4 -> "I Am Legend"
            R.id.imagepeli5 -> "300"
            R.id.imagepeli6 -> "Doctor Strange"
            R.id.imagepeli7 -> "Rogue One: A Star Wars Story"
            R.id.imagepeli8 -> "Assassin's Creed"
            else -> "Título genérico"
        }
    }

    private fun getImageResourceForImage(imageId: Int): Int {
        return when (imageId) {
            R.id.imagepeli1 -> R.drawable.interestellar
            R.id.imagepeli2 -> R.drawable.lobo
            R.id.imagepeli3 -> R.drawable.avatar
            R.id.imagepeli4 -> R.drawable.i_am_a_legend
            R.id.imagepeli5 -> R.drawable.trescientos
            R.id.imagepeli6 -> R.drawable.strange
            R.id.imagepeli7 -> R.drawable.rogue
            R.id.imagepeli8 -> R.drawable.assasin

            else -> R.drawable.pelis
        }
    }

    private fun getTrailerForImage(imageId: Int): String {
        return trailersMap[imageId] ?: "Trailer genérico"
    }
}
