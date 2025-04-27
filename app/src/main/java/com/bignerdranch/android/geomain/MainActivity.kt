package com.bignerdranch.android.geomain


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private var score: Int = 0


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, false),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, false))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            if (currentIndex<questionBank.size-1){
            currentIndex = (currentIndex + 1)
            updateQuestion()
            }
            else{
                //val answerIsTrue = quizViewModel.currentQuestionAnswer
                val intent = ResultActivity.newIntent(this@MainActivity, score)
                startActivity(intent)
            }
        }

        updateQuestion()
    }
        private fun updateQuestion() {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
            val questionTextResId = questionBank[currentIndex].textResId
            questionTextView.setText(questionTextResId)
        }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId : String
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        if (userAnswer == correctAnswer) {
            messageResId = "Правильно"
            score = score+1
        } else {
            messageResId = "Неправильно"
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    }
