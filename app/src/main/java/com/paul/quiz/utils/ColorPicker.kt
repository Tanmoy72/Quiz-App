package com.paul.quiz.utils

object ColorPicker {
    val colors = arrayOf(
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF",
        "#651FFF"
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}