package com.example.proyecto_si

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro)

        val botonVolverLogin = findViewById<Button>(R.id.botonvolverlogin)

        botonVolverLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        val emailRegistro = findViewById<EditText>(R.id.emailregistro)
        val usernameRegistro = findViewById<EditText>(R.id.usernameregistro)
        val passwordRegistro = findViewById<EditText>(R.id.passwordregistro)
        val botonRegistro = findViewById<Button>(R.id.botonregistro)

        botonRegistro.setOnClickListener {
            val email = emailRegistro.text.toString()
            val username = usernameRegistro.text.toString()
            val password = passwordRegistro.text.toString()

            // Validación de campos vacíos
            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de formato de email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Formato de email inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de complejidad de contraseña (por ejemplo, longitud mínima)
            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registrarUsuario(email, password, username)
        }
    }

    private fun registrarUsuario(email: String, password: String, username: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
                    val user = mAuth.currentUser
                    val userEmail = user?.email
                    val userUid = user?.uid
                } else {
                    // Si el registro falla, mostrar un mensaje al usuario.
                    Toast.makeText(this, "Error al registrar, por favor, inténtelo de nuevo.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
