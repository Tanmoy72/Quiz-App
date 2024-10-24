package com.paul.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.paul.quiz.activities.ProfileActivity
import com.paul.quiz.activities.QuestionActivity
import com.paul.quiz.adapters.QuizAdapter
import com.paul.quiz.models.Quiz
import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.SimpleFormatter

class MainActivity : AppCompatActivity() {
     private lateinit var btnDate: ImageButton

    lateinit var firestore: FirebaseFirestore
    lateinit var adapetr:QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    private lateinit var quizRecyclerView: RecyclerView
    lateinit var actionBarDrawerToggle:ActionBarDrawerToggle
    private lateinit var drawer: DrawerLayout
    private lateinit var topBar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setUpView()

    }

    private fun popularDummyData() {
        quizList.add(Quiz("20/09/2024","21/09/2024"))
        quizList.add(Quiz("21/09/2024","22/09/2024"))
        quizList.add(Quiz("22/09/2024","23/09/2024"))
        quizList.add(Quiz("23/09/2024","24/09/2024"))
        quizList.add(Quiz("24/09/2024","25/09/2024"))
        quizList.add(Quiz("25/09/2024","26/09/2024"))
        quizList.add(Quiz("20/09/2024","21/09/2024"))
        quizList.add(Quiz("21/09/2024","22/09/2024"))
        quizList.add(Quiz("22/09/2024","23/09/2024"))
        quizList.add(Quiz("23/09/2024","24/09/2024"))
        quizList.add(Quiz("24/09/2024","25/09/2024"))
        quizList.add(Quiz("25/09/2024","26/09/2024"))
    }

    fun  setUpView(){
        btnDate = findViewById(R.id.btnBca)
        drawer = findViewById(R.id.mainDrawer)
        topBar = findViewById(R.id.topAppBar)
        quizRecyclerView = findViewById(R.id.quizRecyclerView)
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpFireStore()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        //btnDate.visibility = View.GONE
        btnDate.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DatePicker",datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yy")
                val date = dateFormatter.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                Toast.makeText(this,"No Exam",Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener{
                Log.d("DatePicker",datePicker.headerText)
                //Toast.makeText(this,"Not Exam",Toast.LENGTH_SHORT).show()

            }
            datePicker.addOnCancelListener{
                Log.d("DatePicker","DatePicker Cancelled")

            }
        }

    }


    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener{value, error ->
            if (value == null || error !=null){
                Toast.makeText(this,"Error Fetching Data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapetr.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapetr = QuizAdapter(this,quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2) // Use GridLayoutManager for RecyclerView
        quizRecyclerView.adapter = adapetr
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(topBar)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawer,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btnProfile -> { // Use the actual ID for your menu item
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                   finish()

                }
                // Handle more menu items here
              // R.id.
            }
            drawer.closeDrawer(GravityCompat.START)// Close drawer
            true



        }





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}