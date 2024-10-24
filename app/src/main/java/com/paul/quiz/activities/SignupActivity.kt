package com.paul.quiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.paul.quiz.MainActivity
import com.paul.quiz.R

class SignupActivity : AppCompatActivity() {
     lateinit var firebaseAuth: FirebaseAuth
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var conformPassword: EditText
    lateinit var btnSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        conformPassword = findViewById(R.id.conformPassword)
        btnSignUp = findViewById(R.id.btnSinUp)
        // one activity to another
        val buttonLogin: TextView = findViewById(R.id.loginPage)
        buttonLogin.setOnClickListener {
            // Create an Intent to start SecondActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //btn signup
        btnSignUp.setOnClickListener{
            signUp()
        }


    }
    private fun signUp(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = conformPassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(this, "Email and  Password can not blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword){
            Toast.makeText(this, "Password and conFirm password not mach", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                 else{
                    Toast.makeText(this, "Error creating user .", Toast.LENGTH_SHORT).show()
                     }
            }
    }
}