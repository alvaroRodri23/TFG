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

class SeriesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton

    private val trailersMap = mapOf(
        R.id.imageserie1 to "https://www.youtube.com/watch?v=KPLWWIOCOOQ&ab_channel=GameofThrones",
        R.id.imageserie2 to "https://www.youtube.com/watch?v=VwOPA2upeCA&ab_channel=IGN",
        R.id.imageserie3 to "https://www.youtube.com/watch?v=xl8zdCY-abw&ab_channel=NetflixAsia",
        R.id.imageserie4 to "https://www.youtube.com/watch?v=HhesaQXLuRY&ab_channel=TrailerBlend",
        R.id.imageserie5 to "https://www.youtube.com/watch?v=ORa4hPhGrpo&ab_channel=MarvelUK",
        R.id.imageserie6 to "https://www.youtube.com/watch?v=g9PBhyTHhRE&ab_channel=SonyLIV"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.series_activity)

        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)

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
        
        val images = listOf(
            R.id.imageserie1,
            R.id.imageserie2,
            R.id.imageserie3,
            R.id.imageserie4,
            R.id.imageserie5,
            R.id.imageserie6
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
            R.id.imageserie1 -> "Game of Thrones"
            R.id.imageserie2 -> "Gotham"
            R.id.imageserie3 -> "Narcos"
            R.id.imageserie4 -> "Breaking Bad"
            R.id.imageserie5 -> "Luke Cage"
            R.id.imageserie6 -> "Power"
            else -> "Título genérico"
        }
    }

    private fun getImageResourceForImage(imageId: Int): Int {
        return when (imageId) {
            R.id.imageserie1 -> R.drawable.gamethrones
            R.id.imageserie2 -> R.drawable.gotham
            R.id.imageserie3 -> R.drawable.narcos
            R.id.imageserie4 -> R.drawable.breaking
            R.id.imageserie5 -> R.drawable.luke
            R.id.imageserie6 -> R.drawable.power
            else -> R.drawable.series
        }
    }

    private fun getTrailerForImage(imageId: Int): String {
        return trailersMap[imageId] ?: "Trailer genérico"
    }
}
