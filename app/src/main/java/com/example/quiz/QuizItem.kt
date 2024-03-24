package com.example.quiz

data class QuizItem(
    val Id: Int,
    val question : String,
    val answerList:List<String>,
    val correctChoice:String,
    var isAnswerCorrect:Boolean =false

    )

