package com.example.proyecto_si
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity


class Principal : AppCompatActivity() {

    private lateinit var seriesButton: ImageButton
    private lateinit var moviesButton: ImageButton
    private lateinit var seriesScrollView: ScrollView
    private lateinit var moviesScrollView: ScrollView
    private lateinit var botonPerfil: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)
        val botonPerfil = findViewById<ImageButton>(R.id.botonperfil)

        botonPerfil.setOnClickListener {
            val intent = Intent(this, perfilactivity::class.java)
            startActivityForResult(intent, 1)
        }


        val sharedPreferences = getSharedPreferences("Perfil", Context.MODE_PRIVATE)
        val photoId = sharedPreferences.getInt("photoId", -1)
        if (photoId != -1) {
            // Establecer la foto de perfil en el botón
            botonPerfil.setImageResource(photoId)
        }



        seriesButton = findViewById(R.id.botonseries)
        moviesButton = findViewById(R.id.botonpelis)
        seriesScrollView = findViewById(R.id.scrollviewseries)
        moviesScrollView = findViewById(R.id.scrollviewpeliculas)

        seriesButton.setOnClickListener {
            // Mostrar ScrollView de series y ocultar ScrollView de películas
            seriesScrollView.visibility = View.VISIBLE
            moviesScrollView.visibility = View.GONE
        }

        moviesButton.setOnClickListener {
            // Mostrar ScrollView de películas y ocultar ScrollView de series
            seriesScrollView.visibility = View.GONE
            moviesScrollView.visibility = View.VISIBLE
        }

        // Configurar OnClickListener para las imágenes de las series
        findViewById<ImageView>(R.id.imageViewscroll1).setOnClickListener {
            // Aquí puedes pasar información de la serie al Intent
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Jujutsu Kaisen Season 2")
            intent.putExtra("imagen", R.drawable.jjks2)
            intent.putExtra("sinopsis", "A las 7:00 PM del 31 de octubre de 2018, aparece una extraña Pantalla cubriendo todo el Distrito de Shibuya, la cuál permite que los civiles y chamanes entren pero no puedan salir. Los habitantes encerrados dentro de la pantalla piden por la aparición de Satoru Gojo, ya que su aparición es la única condición que necesitan para salir.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=a70_eOnIS3o&ab_channel=CrunchyrollenEspa%C3%B1ol")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll2).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Dr.Stone New World")
            intent.putExtra("imagen", R.drawable.drstone)
            intent.putExtra("sinopsis", "Tras el final de la \"Stone Wars\", los antiguos miembros del imperio de Tsukasa se unen al reino de la Ciencia para construir un barco capaz de surcar los mares y buscar respuestas al misterio global de la petrificación, pero no será sin problemas ya que necesitan nuevos avances para poner en funcionamiento el plan.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=bXgip0F6qdc&ab_channel=Crunchyroll")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll3).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Ataque a los titanes")
            intent.putExtra("imagen", R.drawable.shingeki)
            intent.putExtra("sinopsis", "Un pequeño porcentaje de la humanidad consigue sobrevivir amurallando a sí mismos en una ciudad protegida por las murallas más altas que el más grande de los titanes. En el presente, la ciudad no ha visto a ningún titán en más de 100 años. Pero Eren y su hermana adoptiva Mikasa presencian algo horrible: las murallas de la ciudad son destruidas por un titán colosal que aparece de la nada. No pasará mucho tiempo hasta que los gigantes entran por el hueco abierto en el muro y comienzan a devorar a la gente.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=FRn6xXXF-7s&ab_channel=CrunchyrollenEspa%C3%B1ol")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll4).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Dragon Ball Super")
            intent.putExtra("imagen", R.drawable.db)
            intent.putExtra("sinopsis", "Su trama describe las aventuras de Goku, un guerrero saiyajin experto en artes marciales, diez años después de la derrota de Majin Boo. En su travesía pone a prueba y mejora sus habilidades de pelea, enfrentando oponentes y protegiendo a la Tierra de los seres que quieren conquistarla y exterminar a la humanidad.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=yQJM963be_I&ab_channel=IGN")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll5).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Black Clover")
            intent.putExtra("imagen", R.drawable.bc)
            intent.putExtra("sinopsis", "Black Clover sigue la historia de dos chicos, Asta y Yuno, que crecen en un orfanato de la iglesia de Hage, ubicada en el Reino del Trébol. Ambos se han criado en un mundo donde todas las personas tienen la habilidad de manipular el Poder Mágico.\n")
            intent.putExtra("URL","https://www.youtube.com/watch?v=TiIZ2NZxjMI&ab_channel=CrunchyrollenEspa%C3%B1ol")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll6).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Black Clover Sword of the Wizard King")
            intent.putExtra("imagen", R.drawable.bcfilm)
            intent.putExtra("sinopsis", "Conrad Leto, el rey mago predecesor de Julius Novachrono, una vez respetado por la gente de Clover Kingdom pero de repente se rebeló contra el reino y fue sellado, ha resucitado. Ahora pretende usar la \"Espada Imperial\" para resucitar a los 3 Reyes Magos más temidos de la historia, Edward Avalaché, Princia Funnybunny y Jester Garandaros, y apoderarse del Reino Clover.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=PrgxJ1_sUcs&ab_channel=Netflix")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll7).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Jujutsu Kaisen 0")
            intent.putExtra("imagen", R.drawable.jjk0)
            intent.putExtra("sinopsis", "Yuta Okkotsu es un chico de instituto bastante nervioso con un grave problema: Su amigo Rika se ha convertido en un Curse y no le deja a solas. Ya que Rika no es un Curse ordinario, capta la atención de Satoru Gojo, un profesor del Instituto Jujutsu, donde los exorcistas aprenden a combatir a los Curse. Gojo convence a Yuta para enrolarse en al academia... ¿Podrá aprender Yuta a controlar al Curse que le atormenta?.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=Yl_f0sYMaGk&ab_channel=Crunchyroll")
            startActivity(intent)
        }


        findViewById<ImageView>(R.id.imageViewscroll8).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Dragon Ball Super Broly")
            intent.putExtra("imagen", R.drawable.dbsbroly)
            intent.putExtra("sinopsis", "La paz ha regresado una vez más a la Tierra tras el Torneo de Poder. Al descubrir que en los diferentes universos hay seres increíblemente poderosos que aún no ha visto, Goku tiene intención de seguir entrenando para hacerse aún más fuerte. Entonces, un día, un saiyan llamado Broly al que nunca antes han visto se presenta ante Goku y Vegeta.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=mxUEUrj8Ezw&ab_channel=FandangoLatam")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll9).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Suzume")
            intent.putExtra("imagen", R.drawable.suzume)
            intent.putExtra("sinopsis", "Suzume, de 17 años, descubre una misteriosa puerta en las montañas, y pronto empiezan a aparecer otras puertas por todo Japón. Cuando las puertas se abren, liberan desastres y destrucción, y depende de Suzume volver a cerrarlas.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=2rPPppw6Bhk&ab_channel=CrunchyrollenEspa%C3%B1ol")
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageViewscroll10).setOnClickListener {
            val intent = Intent(this, infopelis::class.java)
            intent.putExtra("titulo", "Guardianes de la noche")
            intent.putExtra("imagen", R.drawable.tren)
            intent.putExtra("sinopsis", "Tanjiro Kamado y sus amigos del Demon Slayer Corps acompañan a Kyōjurō Rengoku, el Flame Hashira, para investigar una misteriosa serie de desapariciones que ocurren dentro de un tren aparentemente infinitamente largo. Poco saben que Enmu, la última de las Lunas Inferiores de los Doce Kizuki, también está a bordo y les ha preparado una trampa.")
            intent.putExtra("URL","https://www.youtube.com/watch?v=-AwLMRgcEoA&ab_channel=Cin%C3%A9polis")
            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Recibe el ID de la foto seleccionada
            val photoId = data?.getIntExtra("photoId", -1)

            // Establece la foto de perfil en el botón
            if (photoId != null && photoId != -1) {

                botonPerfil.setImageResource(photoId)
            }
        }
    }



}



