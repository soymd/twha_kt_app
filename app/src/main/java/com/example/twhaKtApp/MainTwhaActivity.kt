package com.example.twhaKtApp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class MainTwhaActivity : AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_twha)

        webView = findViewById(R.id.twhaView)
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("file:///android_asset/twhaApp/index.html")
    }
}
