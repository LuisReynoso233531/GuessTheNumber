package com.example.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessing)
        val up: Button = findViewById(R.id.up)
        val down: Button = findViewById(R.id.down)
        val generate: Button = findViewById(R.id.generate)
        val guessed: TextView = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :(")
            }
        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :(")
            }
        }

        guessed.setOnClickListener() {
            if (!won) {
                guessings.setText("Adivine, tu numero es el" + num)
                guessed.setText("Volver a jugar")

            }else{
                generate.visibility = View.VISIBLE
                guessed.visibility = View.GONE
                resetValues()

            }
        }

    }

    fun resetValues(){
        minValue=0
        maxValue=0
        num=0
        won=false
    }

    private fun checkingLimits(): Boolean {
        return minValue != maxValue
    }


}
