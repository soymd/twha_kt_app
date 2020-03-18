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

    lateinit var twhaWebView: WebView
    lateinit var nextBtn: Button
    lateinit var showBtn: Button
    //    lateinit var centuryEditText: EditText
    lateinit var answerView: TextView
    lateinit var answerBtn1: Button
    lateinit var answerBtn2: Button
    lateinit var answerBtn3: Button
    lateinit var answerBtn4: Button
    lateinit var answerArr: IntArray
    var year: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tQuestion = TwhaQuestion()

        setContentView(R.layout.activity_twha_question)
        nextBtn = findViewById(R.id.next_button)
        showBtn = findViewById(R.id.show_button)
        answerBtn1 = findViewById(R.id.answer_button_1)
        answerBtn2 = findViewById(R.id.answer_button_2)
        answerBtn3 = findViewById(R.id.answer_button_3)
        answerBtn4 = findViewById(R.id.answer_button_4)
//        centuryEditText = findViewById(R.id.century_form)
        answerView = findViewById(R.id.answer_view)

        twhaWebView = findViewById(R.id.twhaView)
        twhaWebView.settings.javaScriptEnabled = true
        twhaWebView.loadUrl("file:///android_asset/twhaApp/randomYear.html")
        // jsのalert内容から表示されている年数を取得
        twhaWebView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                year = message.toInt()
                answerArr = tQuestion.makeRandomYearArr(year)
                setAnswerButtons(answerArr)
                result.cancel()
                return true
            }
        })

        nextBtn.setOnClickListener {
            twhaWebView.reload()
            showBtn.setText("SHOW")
            listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
                .forEachIndexed { index, button ->
                    button.setBackground(nextBtn.getBackground())
                }
        }
        showBtn.setOnClickListener {
            showBtn.setText(year.toString())
        }
    }

    fun setAnswerButtons(answerArr: IntArray) {
        listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
            .forEachIndexed { index, button ->
                button.setText(answerArr[index].toString())
                button.setOnClickListener {
                    checkAnswer(button)
                }
            }
    }

    fun checkAnswer(btn: Button) {
        val buttonYear: Int = Integer.parseInt(btn.text.toString())
        if (year == buttonYear) {
            btn.setBackgroundColor(Color.GREEN)
        } else {
            btn.setBackgroundColor(Color.RED)

        }


    }


}
