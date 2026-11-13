package com.safareyes.davidmontesdiaz.ut3

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Handler ligado al hilo principal; ejecutará la navegación retrasada
    private val handler = Handler(Looper.getMainLooper())
    private var tareaEjercicio2: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnEjercicio1).setOnClickListener {
            startActivity(Intent(this, activity_ejercicio1::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio3).setOnClickListener {
            startActivity(Intent(this, activity_ejercicio3::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio4).setOnClickListener {
            startActivity(Intent(this, activity_ejercicio4::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio5).setOnClickListener {
            startActivity(Intent(this, activity_ejercicio5::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio6).setOnClickListener {
            startActivity(Intent(this, activity_ejercicio6::class.java))
        }

        val btnEjercicio2 = findViewById<Button>(R.id.btnEjercicio2)
        btnEjercicio2.setOnClickListener {
            val intent = Intent(this, activity_ejercicio2::class.java)

            val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val tarea = Runnable {
                try {
                    pendingIntent.send()
                } catch (e: PendingIntent.CanceledException) {
                    e.printStackTrace()
                }
                btnEjercicio2.isEnabled = true
            }
            tareaEjercicio2 = tarea
            handler.postDelayed(tarea, RETRASO_MS)

            btnEjercicio2.isEnabled = false
            Toast.makeText(this, R.string.ej2_espera, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tareaEjercicio2?.let { handler.removeCallbacks(it) }
    }

    companion object {
        private const val RETRASO_MS = 10_000L
    }
}
