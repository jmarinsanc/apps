package com.example.app_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val miBoton: Button = findViewById(R.id.buttonPeso)
        val entradaPeso: EditText = findViewById(R.id.textPeso)
        val entradaAltura: EditText = findViewById(R.id.textAltura)
        val cuadroSalida: TextView = findViewById(R.id.textResultado)
        val cuadroSalida1: TextView = findViewById(R.id.textResultado2)


        miBoton.setOnClickListener {
            val textoPeso = entradaPeso.text.toString()
            val textoAltura = entradaAltura.text.toString()

            if (textoPeso.isNotEmpty() && textoAltura.isNotEmpty()) {
                val peso = textoPeso.toDoubleOrNull()
                val altura = textoAltura.toDoubleOrNull()

                if (peso != null && altura != null && altura > 0) {
                    val imc = calcularIMC(peso, altura)
                    val mensajeIMC = obtenerMensajeIMC(imc)
                    cuadroSalida1.text = "Tu IMC es: " + imc.toString()
                    cuadroSalida.text = mensajeIMC
                } else {
                    cuadroSalida.text = getString(R.string.error_calculo_imc)
                }
            } else {
                cuadroSalida.text = getString(R.string.error_campos_vacios)
            }
        }

        entradaPeso.setOnClickListener {
            entradaPeso.text.clear()
            entradaAltura.text.clear()
            cuadroSalida.text = ""
            cuadroSalida1.text = ""

        }
    }

    private fun calcularIMC(peso: Double, altura: Double): Double {
        val imc = peso / (altura * altura)
        return "%.2f".format(imc).toDouble()

    }

    private fun obtenerMensajeIMC(imc: Double): String {
        return when {
            imc < 18.5 -> getString(R.string.delgadez)
            imc in 18.5..24.9 -> getString(R.string.buen_estado_forma)
            imc in 25.0..29.9 -> getString(R.string.sobrepeso)
            else -> getString(R.string.obesidad)
        }
    }
}