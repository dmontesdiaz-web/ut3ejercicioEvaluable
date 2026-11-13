package com.safareyes.davidmontesdiaz.ut3

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_ejercicio1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intUrl = findViewById<EditText>(R.id.intUrl)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            var url = intUrl.text.toString().trim()

            if (url.isEmpty() || !Patterns.WEB_URL.matcher(url).matches()) {
                Toast.makeText(this, "Introduce un enlace válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "https://$url"
            }

            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No hay ninguna app para abrir", Toast.LENGTH_SHORT).show()
            }
        }
    }
}