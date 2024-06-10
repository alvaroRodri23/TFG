package com.example.proyecto_si

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class principal2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var botonPerfil: ImageButton
    private lateinit var verticalScrollView: ScrollView
    private lateinit var image4: ImageView
    private lateinit var image5: ImageView
    private lateinit var image6: ImageView
    private val handler = Handler(Looper.getMainLooper())
    private val imageViews: List<ImageView> by lazy {
        listOf(
            findViewById(R.id.image1),
            findViewById(R.id.image2),
            findViewById(R.id.image3),
            findViewById(R.id.image4),
            findViewById(R.id.image5),
            findViewById(R.id.image6),
            findViewById(R.id.image7),
            findViewById(R.id.image8),
            findViewById(R.id.image9)
        )
    }
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal2)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        image4 = findViewById(R.id.imagev4)
        image5 = findViewById(R.id.imagev5)
        image6 = findViewById(R.id.imagev6)



        image4.setOnClickListener {
            val intent = Intent(this, PelisActivity::class.java)
            startActivity(intent)
        }

        image5.setOnClickListener {
            val intent = Intent(this, SeriesActivity::class.java)
            startActivity(intent)
        }

        image6.setOnClickListener {
            val intent = Intent(this, AnimeActivity::class.java)
            startActivity(intent)
        }



        drawer = findViewById(R.id.drawer_layout)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)
        botonPerfil = findViewById(R.id.botonperfil)
        verticalScrollView = findViewById(R.id.verticalScrollView)
        image4 = findViewById(R.id.imagev4)
        image5 = findViewById(R.id.imagev5)
        image6 = findViewById(R.id.imagev6)

        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        startAutoScroll()
    }

    private fun startAutoScroll() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val displayMetrics = resources.displayMetrics
                val imageWidth = displayMetrics.widthPixels
                currentIndex = (currentIndex + 1) % imageViews.size
                val scrollX = currentIndex * imageWidth
                horizontalScrollView.smoothScrollTo(scrollX, 0)
                handler.postDelayed(this, 5000) // Repite cada 5 segundos
            }
        }, 5000)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_one -> showToast("Item 1")
            R.id.nav_item_two -> showToast("Item 2")
            R.id.nav_item_three -> showToast("Item 3")
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
