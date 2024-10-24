package com.paul.quiz.utils

import com.paul.quiz.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.bell_icon,
        R.drawable.calculator_icon,
        R.drawable.education_line_ruler_icon,
        R.drawable.letter_icon,
        R.drawable.bell_icon,
        R.drawable.bell_icon,
        R.drawable.bell_icon,
        R.drawable.bell_icon,
        R.drawable.bell_icon
    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon+ 1) % icons.size
        return icons[currentIcon]
    }
}