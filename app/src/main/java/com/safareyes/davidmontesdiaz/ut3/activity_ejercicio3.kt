package com.safareyes.davidmontesdiaz.ut3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_ejercicio3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEjercicio2 = findViewById<View>(R.id.btn2)
        val btnEjercicio3 = findViewById<View>(R.id.btn3)
        val btnEjercicio4 = findViewById<View>(R.id.btn4)

        btnEjercicio2.setOnClickListener {
            startActivity(Intent(this, activity_ejercicio3b::class.java))
        }

        btnEjercicio3.setOnClickListener {
            startActivity(Intent(this, activity_ejercicio3c::class.java))
        }

        btnEjercicio4.setOnClickListener {
            startActivity(Intent(this, activity_ejercicio3d::class.java))
        }
    }
}