package com.example.twhaKtApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataArray = arrayOf("The World Historical Atlas", "出題")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, dataArray
        )
        val listView = findViewById(R.id.main_menu_list) as ListView
        listView.setAdapter(adapter)

        listView.setOnItemClickListener { parent, view, position, id ->
            val itemTextView: TextView = view.findViewById(android.R.id.text1)

            when (position) {
                0 -> {
                    Log.i("debug", "0")
                    itemTextView.setOnClickListener {
                        val intent = Intent(application, MainTwhaActivity::class.java)
                        startActivity(intent)
                    }
                }
                1 -> {
                    Log.i("debug", "1")
                    itemTextView.setOnClickListener {
                        val intent = Intent(application, TwhaQuestionActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
