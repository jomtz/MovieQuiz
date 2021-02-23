package com.josmartinez.moviequiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MixActivity : AppCompatActivity() {

    private lateinit var playBackButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mix)

        playBackButton = findViewById(R.id.play_back_button)

        playBackButton.setOnClickListener {
            val intent = Intent(this@MixActivity, MainActivity::class.java)
            startActivity(intent)


        }

    }




}