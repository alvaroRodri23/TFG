package com.example.proyecto_si

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase


class Principal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i= 0;i <6;++1){

            View view = inflater.inflate(R.layout.item, gallery,false);

            ImageView imageview = view.findViewById(R.id.imageViewscroll1)
            imageviewscroll1.setImageResource(R.drawable.jjks2)
            gallery.addView()view
        }
    }




}