package com.example.proyecto_si

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.Button
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

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Encuentra los botones por su ID
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)

        // Asigna un OnClickListener a cada botón
        button1.setOnClickListener {
            val intent = Intent(this, principal2::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, PelisActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent(this, SeriesActivity::class.java)
            startActivity(intent)
        }

        button5.setOnClickListener {
            val intent = Intent(this, AnimeActivity::class.java)
            startActivity(intent)
        }

        button6.setOnClickListener {
            val intent = Intent(this, perfilactivity::class.java)
            startActivity(intent)
        }



        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        image4 = findViewById(R.id.imagev4)
        image5 = findViewById(R.id.imagev5)
        image6 = findViewById(R.id.imagev6)

        // Configuración de los listeners de los ImageView
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

        // Configuración del drawer y toggle
        drawer = findViewById(R.id.drawer_layout)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)
        botonPerfil = findViewById(R.id.botonperfil)
        verticalScrollView = findViewById(R.id.verticalScrollView)

        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)



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
            R.id.nav_item_one-> {
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
