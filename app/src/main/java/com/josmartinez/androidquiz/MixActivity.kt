package com.josmartinez.androidquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

const val EXTRA_ANSWER_SHOWN = "com.josmartinez.androidquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.josmartinez.androidquiz.answer_is_true"

class MixActivity : AppCompatActivity() {

    private lateinit var showAnswerButton: Button

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mix)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            when{
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            setAnswerShownResult()
        }
    }


    private fun setAnswerShownResult() {
        val data = Intent().apply {
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(packageContext, MixActivity::class.java)
                .apply { putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue) }
        }
    }

}