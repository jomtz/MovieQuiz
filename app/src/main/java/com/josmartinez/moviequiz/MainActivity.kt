package com.josmartinez.moviequiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"


class MainActivity : AppCompatActivity() {

    private lateinit var firstEmptyHeart: ImageView
    private lateinit var firstFullHeart: ImageView

    private lateinit var secondEmptyHeart: ImageView
    private lateinit var secondFullHeart: ImageView

    private lateinit var lastEmptyHeart: ImageView
    private lateinit var lastFullHeart: ImageView

    private lateinit var trueButton: ImageButton
    private lateinit var falseButton: ImageButton
    private lateinit var playAgainButton: Button
    private lateinit var questionTextView: TextView

    private var oneHeart: Int = 0

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        firstEmptyHeart = findViewById(R.id.first_empty_heart)
        firstFullHeart = findViewById(R.id.first_full_heart)
        secondEmptyHeart = findViewById(R.id.second_empty_heart)
        secondFullHeart = findViewById(R.id.second_full_heart)
        lastEmptyHeart = findViewById(R.id.last_empty_heart)
        lastFullHeart = findViewById(R.id.last_full_heart)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        playAgainButton = findViewById(R.id.play_again_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        //Update to Next Question when is QuestionText is clicked
        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        playAgainButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MixActivity::class.java)
            startActivity(intent)
        }

        updateQuestion()

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSavedInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        if (userAnswer == correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }
        else {
            oneHeart = (oneHeart + 1)
            removeHeart(oneHeart)
        }
        quizViewModel.moveToNext()
        updateQuestion()

    }


    private fun removeHeart(currentHeart: Int) {
        val loseHeart = oneHeart
        when (loseHeart == currentHeart) {
            (loseHeart == 1) -> firstIncorrect()
            (loseHeart == 2) -> secondIncorrect()
            (loseHeart == 3) -> thirdIncorrect()
            (loseHeart == 4) -> fourthIncorrect()
            else -> {}
        }
    }


    private fun firstIncorrect() {
        firstFullHeart.visibility = ImageView.GONE
        firstEmptyHeart.visibility = ImageView.VISIBLE
    }

    private fun secondIncorrect() {
        secondFullHeart.visibility = ImageView.GONE
        secondEmptyHeart.visibility = ImageView.VISIBLE
    }

    private fun thirdIncorrect() {
        lastFullHeart.visibility = ImageView.GONE
        lastEmptyHeart.visibility = ImageView.VISIBLE
    }

    private fun fourthIncorrect() {
        val intent = Intent(this@MainActivity, MixActivity::class.java)
        startActivity(intent)
    }


}

