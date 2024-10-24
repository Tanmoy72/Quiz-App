package com.paul.quiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.paul.quiz.MainActivity
import com.paul.quiz.R

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var txtEmail: TextView
    lateinit var btnLogout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        firebaseAuth = FirebaseAuth.getInstance()
        txtEmail = findViewById(R.id.txtEmail)
        btnLogout = findViewById(R.id.btnLogout)
        txtEmail.text = firebaseAuth.currentUser?.email

        btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }



    }


}