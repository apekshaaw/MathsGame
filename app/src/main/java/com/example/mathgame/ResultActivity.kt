package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textViewScore: TextView = findViewById(R.id.textView_score)
        val buttonPlayAgain: Button = findViewById(R.id.button_play_again)
        val buttonExit: Button = findViewById(R.id.button_exit)

        val score = intent.getIntExtra("score", 0)
        val correct = intent.getBooleanExtra("correct", false)
        val lives = intent.getIntExtra("lives", 3)

        textViewScore.text = if (correct) {
            "Congratulations! Your answer is correct.\nYour Score: $score\nLives Remaining: $lives"
        } else {
            "Oops! Wrong answer.\nYour Score: $score\nLives Remaining: $lives"
        }

        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonExit.setOnClickListener {
            finishAffinity() // This will close the app
        }
    }
}
