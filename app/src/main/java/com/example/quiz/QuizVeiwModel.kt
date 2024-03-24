package com.example.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizVeiwModel:ViewModel (){
var quizList:MutableList<QuizItem> = mutableListOf(
    QuizItem(
        1,
         "What is the capital of France?",
         listOf("Paris", "London", "Berlin", "Madrid"),
         "Paris",
    ),
    QuizItem(
        2,
         "What is the capital of Germany?",
          listOf("Paris", "London", "Berlin", "Madrid"),
         "Berlin",
    ),
    QuizItem(
        3,
        "What is the capital of Spain?",
         listOf("Paris", "London", "Berlin", "Madrid"),
         "Madrid",
    ),
    QuizItem(
        4,
         "What is the capital of England?",
         listOf("Paris", "London", "Berlin", "Madrid"),
        "London",
    )
)
val score = mutableStateOf("")

    fun checkAnswer (quizItem: QuizItem, answer:String) {
        quizList = quizList.map {
            if (it.Id == quizItem.Id && answer == it.correctChoice) {
                it.copy(isAnswerCorrect = true)
            } else if (it.Id == quizItem.Id && answer != it.correctChoice) {
                it.copy(isAnswerCorrect = false)
            } else {
                it
            }

        }.toMutableList()
    }
    fun onSubmit(){//mutable must be in a compose scope to be updated when
val numOfCorrectAnswer=quizList.filter { it.isAnswerCorrect }.size
        score.value="you got ${numOfCorrectAnswer} out of ${quizList.size}"
    }
}