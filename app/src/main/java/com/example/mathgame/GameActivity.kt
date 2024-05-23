package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private var score = 0
    private var lives = 3
    private lateinit var operation: String
    private lateinit var textViewQuestion: TextView
    private lateinit var editTextAnswer: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        operation = intent.getStringExtra("operation") ?: "addition"
        textViewQuestion = findViewById(R.id.textView_question)
        editTextAnswer = findViewById(R.id.editText_answer)
        buttonSubmit = findViewById(R.id.button_submit)

        generateQuestion()

        buttonSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    private fun generateQuestion() {
        val number1 = Random.nextInt(1, 10)
        val number2 = Random.nextInt(1, 10)
        val question = when (operation) {
            "addition" -> "$number1 + $number2"
            "subtraction" -> "$number1 - $number2"
            "multiplication" -> "$number1 * $number2"
            else -> "$number1 + $number2"
        }
        textViewQuestion.text = question
    }

    private fun checkAnswer() {
        val question = textViewQuestion.text.toString()
        val correctAnswer = when (operation) {
            "addition" -> question.split(" + ").let { it[0].toInt() + it[1].toInt() }
            "subtraction" -> question.split(" - ").let { it[0].toInt() - it[1].toInt() }
            "multiplication" -> question.split(" * ").let { it[0].toInt() * it[1].toInt() }
            else -> 0
        }

        val userAnswer = editTextAnswer.text.toString().toIntOrNull()
        if (userAnswer == correctAnswer) {
            score += 10
            showResult(true)
        } else {
            lives -= 1
            if (lives <= 0) {
                endGame()
            } else {
                showResult(false)
            }
        }
    }

    private fun showResult(correct: Boolean) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correct", correct)
        intent.putExtra("score", score)
        intent.putExtra("lives", lives)
        startActivity(intent)
    }

    private fun endGame() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
        finish()
    }
}
