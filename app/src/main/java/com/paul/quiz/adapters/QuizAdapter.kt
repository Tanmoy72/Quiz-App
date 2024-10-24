package com.paul.quiz.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.paul.quiz.R
import com.paul.quiz.activities.QuestionActivity
import com.paul.quiz.models.Quiz
import com.paul.quiz.utils.ColorPicker
import com.paul.quiz.utils.IconPicker


class QuizAdapter(val context: Context,val quizzes:List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context, quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }
    }
    inner class QuizViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}