package com.example.twhaKtApp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class TwhaQuestionActivity : AppCompatActivity() {

    private val twhaWebView: WebView by lazy { findViewById<WebView>(R.id.twhaView) }
    private val questionBtn: Button by lazy { findViewById<Button>(R.id.question_button) }
    private val backBtn: Button by lazy { findViewById<Button>(R.id.back_button) }
    private val answerBtn1: Button by lazy { findViewById<Button>(R.id.answer_button_1) }
    private val answerBtn2: Button by lazy { findViewById<Button>(R.id.answer_button_2) }
    private val answerBtn3: Button by lazy { findViewById<Button>(R.id.answer_button_3) }
    private val answerBtn4: Button by lazy { findViewById<Button>(R.id.answer_button_4) }

    private lateinit var answerList: MutableList<Int>
    private var year = 0
    private var userId = 1;
    private var difficulty = 1;
    private val repository = TwhaRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tQuestion = TwhaQuestion()

        setContentView(R.layout.activity_twha_question)

        //JSを有効にしてWebViewにhtmlファイルを呼び出し
        this.twhaWebView.settings.javaScriptEnabled = true
        this.twhaWebView.loadUrl("file:///android_asset/twhaApp/webView.html")

        // DBにアクセスして難易度を取得
        this.difficulty = repository.readDifficulty(this, userId)

        //出題ボタン
        this.questionBtn.setOnClickListener {
            //乱数で発生させた年号を取得
            this.year = tQuestion.randomYear()
            //年号から選択肢の候補を取得
            this.answerList = tQuestion.makeRandomYearList(year, difficulty)

            //年号のマップを読込 & year_barなどを非表示
            changeYearJS(this.year)
            hideYearBarAndText()
            updateWebViewJS()

            setAnswerButtons(this.answerList)
            this.backBtn.visibility = View.VISIBLE
            this.questionBtn.text = "次へ"

            listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
                .forEachIndexed { index, button ->
                    button.visibility = View.VISIBLE
                    button.background = questionBtn.background
                }
        }

        //戻るボタン
        this.backBtn.setOnClickListener {
            showYearBarAndText()
            updateWebViewJS()
            this.questionBtn.text = "出題"
            this.backBtn.visibility = View.GONE
            this.questionBtn.visibility = View.VISIBLE

            //回答用ボタンの色を戻す & 非表示に設定
            listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
                .forEachIndexed { index, button ->
                    button.visibility = View.INVISIBLE
                    button.background = questionBtn.background
                }
        }
    }

    fun showYearBarAndText() {
        //year_barの高さ設定をもとに戻す & year_barとyear_textのstyle.display="none"を解除
        this.twhaWebView.evaluateJavascript("window.window.setYearBarHeight(32)", null)
        this.twhaWebView.evaluateJavascript("window.showYearBar()", null)
        this.twhaWebView.evaluateJavascript("window.showYearText()", null)
    }

    fun hideYearBarAndText() {
        //year_barの高さ設定を0 & year_barとyear_textのstyle.display="none"に設定
        this.twhaWebView.evaluateJavascript("window.window.setYearBarHeight(0)", null)
        this.twhaWebView.evaluateJavascript("window.hideYearBar()", null)
        this.twhaWebView.evaluateJavascript("window.hideYearText()", null)
    }

    fun updateWebViewJS() {
        //webViewの各パーツを再読み込み
        this.twhaWebView.evaluateJavascript("window.updateWebView()", null)
    }

    fun changeYearJS(year: Int) {
        this.twhaWebView.evaluateJavascript("window.changeYear($year)", null)
    }

    fun changeMapJS(x: Int, y: Int) {
        this.twhaWebView.evaluateJavascript("window.changeMap($x,$y)", null)
    }

    fun setAnswerButtons(answerList: MutableList<Int>) {
        listOf(answerBtn1, answerBtn2, answerBtn3, answerBtn4)
            .forEachIndexed { index, button ->
                button.text = answerList[index].toString()
                button.setOnClickListener {
                    checkAnswer(button)
                }
            }
    }

    fun checkAnswer(btn: Button) {
        val btnYear: Int = Integer.parseInt(btn.text.toString())
        if (this.year == btnYear) {
            btn.setBackgroundColor(Color.GREEN)
        } else {
            btn.setBackgroundColor(Color.RED)
        }
        saveAnswerData(btnYear)
    }

    private fun saveAnswerData(btnYear: Int) {
        val option1 = Integer.parseInt(answerBtn1.text.toString())
        val option2 = Integer.parseInt(answerBtn2.text.toString())
        val option3 = Integer.parseInt(answerBtn3.text.toString())
        val option4 = Integer.parseInt(answerBtn4.text.toString())
        val correct = if (year == btnYear) {
            1
        } else {
            0
        }
        repository.insertAnswer(
            this, userId, difficulty, year, option1,
            option2, option3, option4, btnYear, correct
        )
    }
}
