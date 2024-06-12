package com.example.proyecto_si

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
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
    private lateinit var verticalScrollView: ScrollView
    private lateinit var image4: ImageView
    private lateinit var image5: ImageView
    private lateinit var image6: ImageView
    private lateinit var botonPerfil: ImageButton
    private lateinit var botonHome: ImageButton
    private lateinit var botonAnaliticas: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal2)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        image4 = findViewById(R.id.imagev4)
        image5 = findViewById(R.id.imagev5)
        image6 = findViewById(R.id.imagev6)
        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)


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


        drawer = findViewById(R.id.drawer_layout)
        botonPerfil = findViewById(R.id.botonperfil)
        botonHome = findViewById(R.id.botonhome)
        botonAnaliticas = findViewById(R.id.botonanaliticas)
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
