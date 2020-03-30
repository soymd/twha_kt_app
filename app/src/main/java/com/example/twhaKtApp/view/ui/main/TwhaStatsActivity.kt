package com.example.twhaKtApp.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twhaKtApp.R

class TwhaStatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twha_stats)
        supportFragmentManager.beginTransaction().replace(
            R.id.container_main_fragment,
            StatsFragment()
        ).commit()
    }
}
