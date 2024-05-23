package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddition: Button = findViewById(R.id.button_addition)
        val buttonSubtraction: Button = findViewById(R.id.button_subtraction)
        val buttonMultiplication: Button = findViewById(R.id.button_multiplication)

        buttonAddition.setOnClickListener {
            startGame("addition")
        }

        buttonSubtraction.setOnClickListener {
            startGame("subtraction")
        }

        buttonMultiplication.setOnClickListener {
            startGame("multiplication")
        }
    }

    private fun startGame(operation: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("operation", operation)
        startActivity(intent)
    }
}
