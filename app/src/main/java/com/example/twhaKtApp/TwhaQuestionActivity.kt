package com.example.twhaKtApp

import android.graphics.Color
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TwhaQuestionActivity : AppCompatActivity() {

    private lateinit var twhaWebView: WebView
    private lateinit var nextBtn: Button
    private lateinit var showBtn: Button
    private lateinit var answerView: TextView
    private lateinit var answerBtn1: Button
    private lateinit var answerBtn2: Button
    private lateinit var answerBtn3: Button
    private lateinit var answerBtn4: Button
    private lateinit var answerArr: MutableList<Int>
    private var year = 0
    private var userId = 1;
    private var difficulty = 1;
    private val repository = TwhaRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tQuestion = TwhaQuestion()

        setContentView(R.layout.activity_twha_question)
        this.answerBtn1 = findViewById(R.id.answer_button_1)
        this.answerBtn2 = findViewById(R.id.answer_button_2)
        this.answerBtn3 = findViewById(R.id.answer_button_3)
        this.answerBtn4 = findViewById(R.id.answer_button_4)
        this.nextBtn = findViewById(R.id.next_button)
        this.showBtn = findViewById(R.id.show_button)
        this.answerView = findViewById(R.id.answer_view)

        this.twhaWebView = findViewById(R.id.twhaView)
        this.twhaWebView.settings.javaScriptEnabled = true
        this.twhaWebView.loadUrl("file:///android_asset/twhaApp/randomYear.html")
        // jsのalert内容から表示されている年数を取得
        this.difficulty = repository.readDifficulty(this, userId)

        twhaWebView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                year = message.toInt()
                answerArr = tQuestion.makeRandomYearArr(year, difficulty)
                setAnswerButtons(answerArr)
                result.cancel()
                return true
            }
        })

        this.nextBtn.setOnClickListener {
            twhaWebView.reload()
            this.showBtn.text = ""
            listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
                .forEachIndexed { index, button ->
                    button.background = nextBtn.background
                }
        }
        this.showBtn.setOnClickListener {
            showBtn.text = year.toString()
        }

    }

    fun setAnswerButtons(answerArr: MutableList<Int>) {
        listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
            .forEachIndexed { index, button ->
                button.text = answerArr[index].toString()
                button.setOnClickListener {
                    checkAnswer(button)
                }
            }
    }

    fun checkAnswer(btn: Button) {
        val btnYear: Int = Integer.parseInt(btn.text.toString())
        if (year == btnYear) {
            btn.setBackgroundColor(Color.GREEN)
        } else {
            btn.setBackgroundColor(Color.RED)
        }
        putAnswer(btnYear)
    }

    private fun putAnswer(btnYear: Int) {
        val option1 = Integer.parseInt(answerBtn1.text.toString())
        val option2 = Integer.parseInt(answerBtn2.text.toString())
        val option3 = Integer.parseInt(answerBtn3.text.toString())
        val option4 = Integer.parseInt(answerBtn4.text.toString())
        val correct = if (year == btnYear) {
            1
        } else {
            0
        }
        repository.insertData(
            this, userId, difficulty, year, option1,
            option2, option3, option4, btnYear, correct
        )
    }
}
