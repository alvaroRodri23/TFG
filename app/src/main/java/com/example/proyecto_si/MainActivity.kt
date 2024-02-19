package com.example.proyecto_si

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emaillogin)
        val passwordEditText = findViewById<EditText>(R.id.passwordperfil)
        val loginButton = findViewById<Button>(R.id.botonlogin)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Guardar email y contraseña en SharedPreferences
                        val sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                        sharedPreferences.edit().apply {
                            putString("email", email)
                            putString("password", password)
                            apply()
                        }

                        // Redirigir a la actividad perfilactivity
                        val intent = Intent(this, perfilactivity::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)
                        finish() // Esto asegura que el usuario no pueda volver atrás presionando el botón Atrás del dispositivo
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Correo incorrecto.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val botonRegistro = findViewById<Button>(R.id.botonregistro)
        botonRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
