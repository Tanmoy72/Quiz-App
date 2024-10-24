package com.paul.quiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.paul.quiz.R
import com.paul.quiz.activities.QuestionActivity
import com.paul.quiz.models.Question

class OptionAdapters(val context: QuestionActivity, val question: Question):
    RecyclerView.Adapter<OptionAdapters.optionViewHolder>() {
        private var options:List<String> = listOf(question.option1,question.option2,question.option3,question.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.option_item, parent, false)
        return optionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: optionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }

    }
    inner class optionViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quizOption)
    }
}