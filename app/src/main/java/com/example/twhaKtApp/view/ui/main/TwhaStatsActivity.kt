package com.example.twhaKtApp.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twhaKtApp.R
import com.example.twhaKtApp.TwhaRepository

class TwhaStatsActivity : AppCompatActivity() {

    private var userId = 1;

    private val repository = TwhaRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twha_stats)

        repository.readAnswers(this, userId)


        supportFragmentManager.beginTransaction().replace(
            R.id.container_main_fragment,
            StatsFragment()
        ).commit()
    }
}
