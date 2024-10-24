package com.paul.quiz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.paul.quiz.R
import com.paul.quiz.adapters.OptionAdapters
import com.paul.quiz.models.Question
import com.paul.quiz.models.Quiz

class QuestionActivity : AppCompatActivity() {
    var quizzes:MutableList<Quiz>? = null
    var questions:MutableMap<String, Question>? = null
    var index = 1
    private lateinit var description: TextView
    private lateinit var optionList: RecyclerView
    private lateinit var btnPrevious : Button
    private lateinit var btnNext : Button
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        initializeViews()
        setUPFireStore()
        setUpBtnEvent()
    }
    private fun initializeViews() {
        btnPrevious = findViewById(R.id.btmBack)
        btnNext = findViewById(R.id.btnNext)
        btnSubmit = findViewById(R.id.btnSubmit)
        description = findViewById(R.id.description)
        optionList = findViewById(R.id.optionList)
    }

    private fun setUpBtnEvent() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }

        btnNext.setOnClickListener {
            index++
            bindViews()
        }

        btnSubmit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)

        }
    }


    private fun setUPFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if(date != null){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty){
                      quizzes = it.toObjects(Quiz::class.java)
                      questions = quizzes!![0].questions
                        bindViews()
                    }

                }
        }

    }

    private fun bindViews() {

        btnPrevious.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnSubmit.visibility = View.GONE

        if (index == 1) {
            btnNext.visibility = View.VISIBLE
        } else if (index == questions!!.size) {
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        } else {
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            description.text = it.description
            val optionAdapters = OptionAdapters(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapters
            optionList.setHasFixedSize(true)
        }

    }
}