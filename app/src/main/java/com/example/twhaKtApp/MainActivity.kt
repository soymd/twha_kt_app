package com.example.twhaKtApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var difficultyBtnGroup: RadioGroup
    private lateinit var difficultyBtn1: RadioButton
    private lateinit var difficultyBtn2: RadioButton
    private lateinit var difficultyBtn3: RadioButton
    private val repository = TwhaRepository()
    private val userId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.difficultyBtnGroup = findViewById(R.id.difficulty_button_group)
        this.difficultyBtn1 = findViewById(R.id.difficulty_button_1)
        this.difficultyBtn2 = findViewById(R.id.difficulty_button_2)
        this.difficultyBtn3 = findViewById(R.id.difficulty_button_3)

        val dataArray = arrayOf("The World Historical Atlas", "出題")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, dataArray
        )
        val listView = findViewById<ListView>(R.id.main_menu_list)
        listView.adapter = adapter

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

        this.difficultyBtnGroup.setOnCheckedChangeListener { _, checkedId: Int ->
            when (checkedId) {
                R.id.difficulty_button_1 -> {
                    Toast.makeText(
                        this,
                        "難易度1",
                        Toast.LENGTH_SHORT
                    ).show()
                    setDifficulty(1)
                }
                R.id.difficulty_button_2 -> {
                    Toast.makeText(
                        this,
                        "難易度2",
                        Toast.LENGTH_SHORT
                    ).show()
                    setDifficulty(2)
                }
                R.id.difficulty_button_3 -> {
                    Toast.makeText(
                        this,
                        "難易度3",
                        Toast.LENGTH_SHORT
                    ).show()
                    setDifficulty(3)
                }
                else -> throw IllegalArgumentException("not supported")
            }
        }
        initDatabase()

        when (getDifficulty()) {
            1 -> this.difficultyBtnGroup.check(R.id.difficulty_button_1)
            2 -> this.difficultyBtnGroup.check(R.id.difficulty_button_2)
            3 -> this.difficultyBtnGroup.check(R.id.difficulty_button_3)
        }
    }

    fun setDifficulty(difficulty: Int) {
        repository.insertDifficulty(this, userId, difficulty)
    }

    fun getDifficulty(): Int {
        return repository.readDifficulty(this, userId)
    }

    fun initDatabase() {
        val adb = AssetDatabaseOpenHelper(this)
        adb.openDatabase()
    }
}
