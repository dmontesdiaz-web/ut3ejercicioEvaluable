package com.safareyes.davidmontesdiaz.ut3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_ejercicio5 : AppCompatActivity() {

    lateinit var txtPantalla: TextView
    var primerNumero: Double? = null
    var operacion: String? = null
    var empezarNumeroNuevo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtPantalla = findViewById(R.id.txtPantalla)

        val botonesNumero = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        for (id in botonesNumero) {
            val boton = findViewById<Button>(id)
            boton.setOnClickListener { escribirDigito(boton.text.toString()) }
        }

        val botonesOperacion = listOf(R.id.btnSumar, R.id.btnRestar, R.id.btnMultiplicar, R.id.btnDividir)
        for (id in botonesOperacion) {
            val boton = findViewById<Button>(id)
            boton.setOnClickListener { elegirOperacion(boton.text.toString()) }
        }

        findViewById<Button>(R.id.btnDecimal).setOnClickListener {
            escribirDecimal()
        }

        findViewById<Button>(R.id.btnIgual).setOnClickListener {
            calcularResultado()
        }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            restablecer()
        }
    }

    fun escribirDigito(digito: String) {
        val actual = txtPantalla.text.toString()
        if (empezarNumeroNuevo || actual == "0" || actual == "Error") {
            txtPantalla.text = digito
            empezarNumeroNuevo = false
        } else {
            txtPantalla.text = actual + digito
        }
    }

    fun escribirDecimal() {
        if (empezarNumeroNuevo || txtPantalla.text.toString() == "Error") {
            txtPantalla.text = "0."
            empezarNumeroNuevo = false
        } else if (!txtPantalla.text.contains(".")) {
            txtPantalla.append(".")
        }
    }

    fun elegirOperacion(nuevaOperacion: String) {
        if (operacion != null && !empezarNumeroNuevo) {
            calcularResultado()
        }
        primerNumero = txtPantalla.text.toString().toDoubleOrNull()
        operacion = nuevaOperacion
        empezarNumeroNuevo = true
    }

    fun calcularResultado() {
        val a = primerNumero
        val b = txtPantalla.text.toString().toDoubleOrNull()
        val op = operacion
        if (a == null || b == null || op == null) return

        val resultado = when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b == 0.0) null else a / b
            else -> null
        }

        txtPantalla.text = if (resultado == null) "Error" else formatear(resultado)
        primerNumero = null
        operacion = null
        empezarNumeroNuevo = true
    }

    fun restablecer() {
        txtPantalla.text = "0"
        primerNumero = null
        operacion = null
        empezarNumeroNuevo = true
    }

    fun formatear(numero: Double): String {
        return if (numero % 1 == 0.0 && numero < Long.MAX_VALUE) {
            numero.toLong().toString()
        } else {
            numero.toString()
        }
    }
}
