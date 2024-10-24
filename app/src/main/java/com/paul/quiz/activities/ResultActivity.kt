package com.paul.quiz.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.paul.quiz.MainActivity
import com.paul.quiz.R
import com.paul.quiz.models.Quiz

class ResultActivity : AppCompatActivity() {
    lateinit var quiz:Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        setUpViews()
    }
    private fun setUpViews() {
       val quizData = intent.getStringExtra("QUIZ")
         quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        setAnswerView()

    }

    private fun setAnswerView() {
        val txtAnswer = findViewById<TextView>(R.id.txtAnswer)
        val builder = StringBuilder("")
        var index = 1
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
            index++
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }



    }

    private fun calculateScore() {
        val txtScore = findViewById<TextView>(R.id.txtScore)
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Your Score : $score"


    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Clears the activity stack
        startActivity(intent)
        finish() // Optional: closes the ResultActivity
    }

}