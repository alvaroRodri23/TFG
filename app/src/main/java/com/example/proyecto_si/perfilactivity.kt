package com.example.proyecto_si

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class perfilactivity : AppCompatActivity() {

    private lateinit var imageViewPerfil: ImageView
    private lateinit var recyclerViewProfileOptions: RecyclerView
    private lateinit var usernameTextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var passwordEditText: EditText
    private lateinit var showHidePasswordButton: Button

    private lateinit var mAuth: FirebaseAuth

    private var isPasswordVisible = false

    private val photos = listOf(
        R.drawable.senkupfp, R.drawable.astapfp, R.drawable.itadoripfp,
        R.drawable.gokupfp, R.drawable.yutapfp, R.drawable.suzumepfp,
        R.drawable.vegetapfp, R.drawable.makipfp, R.drawable.erenpfp, R.drawable.tokitopfp
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        val botonPrincipal2 = findViewById<Button>(R.id.botonprincipal2)

        botonPrincipal2.setOnClickListener {
            val intent = Intent(this, principal2::class.java)
            startActivity(intent)
        }



        mAuth = FirebaseAuth.getInstance()


        imageViewPerfil = findViewById(R.id.imageButton3)
        recyclerViewProfileOptions = findViewById(R.id.recyclerViewProfileOptions)
        usernameTextView = findViewById(R.id.usernameperfil)
        passwordTextView = findViewById(R.id.passwordperfil)
        passwordEditText = findViewById(R.id.passwordperfil)
        showHidePasswordButton = findViewById(R.id.showHidePasswordButton)


        togglePasswordVisibility()


        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")


        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("email", email)
            putString("password", password)
            apply()
        }


        usernameTextView.text = "Username: $email"
        passwordTextView.text = "Password: $password"


        recyclerViewProfileOptions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ProfileOptionsAdapter(photos) { photo ->
            imageViewPerfil.setImageResource(photo)
            // Guardar la foto de perfil permanentemente
            val sharedPreferences = getSharedPreferences("Perfil", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("photoId", photo).apply()
            setResult(Activity.RESULT_OK)
        }
        recyclerViewProfileOptions.adapter = adapter


        val sharedPreferencesPhoto = getSharedPreferences("Perfil", Context.MODE_PRIVATE)
        val savedPhotoId = sharedPreferencesPhoto.getInt("photoId", -1)
        if (savedPhotoId != -1) {
            imageViewPerfil.setImageResource(savedPhotoId)
        }

        showHidePasswordButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
        }

    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordEditText.transformationMethod = null
            showHidePasswordButton.text = "Ocultar"
        } else {
            passwordEditText.transformationMethod = android.text.method.PasswordTransformationMethod()
            showHidePasswordButton.text = "Mostrar"
        }
        passwordEditText.setSelection(passwordEditText.text.length)
    }
}
