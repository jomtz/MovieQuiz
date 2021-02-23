package com.josmartinez.moviequiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_number_one, false),
        Question(R.string.question_number_two, true),
        Question(R.string.question_number_three, true),
        Question(R.string.question_number_four, false),
        Question(R.string.question_number_five, false),
        Question(R.string.question_number_six, false),
        Question(R.string.question_number_seven, true),
        Question(R.string.question_number_eight, false),
        Question(R.string.question_number_nine, false),
        Question(R.string.question_number_ten, true),
        Question(R.string.question_number_eleven, false),
        Question(R.string.question_number_twelve, true),
        Question(R.string.question_number_thirteen, true),
        Question(R.string.question_number_fourteen, true))

    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer

    val currentQuestionText: Int get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }


}


