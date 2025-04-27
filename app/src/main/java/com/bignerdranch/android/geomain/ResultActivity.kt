package com.bignerdranch.android.geomain

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

private const val EXTRA_SCORE =
    "com.bignerdranch.android.geomain.score"
class ResultActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        score = intent.getIntExtra(EXTRA_SCORE, 0)
        answerTextView = findViewById(R.id.score_text_view)
        answerTextView.text = "Ваш результат: " +score.toString()
    }
    companion object {
        fun newIntent(packageContext: Context, score: Int): Intent {
            return Intent(packageContext, ResultActivity::class.java).apply {
                putExtra(EXTRA_SCORE, score)
            }
        }
    }
}