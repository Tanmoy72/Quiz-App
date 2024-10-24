package com.paul.quiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.paul.quiz.MainActivity
import com.paul.quiz.R
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_intro)
        val getStart:Button = findViewById(R.id.getStarted)
        val auth:FirebaseAuth = FirebaseAuth.getInstance()
        //
        if (auth.currentUser != null){
            Toast.makeText(this,"User already logged in",Toast.LENGTH_SHORT).show()
            redirect("Main")
        }

        getStart.setOnClickListener{
            redirect("LOGIN")
        }

    }
    private fun redirect(name: String){
        val intent = when(name){
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()

    }

}