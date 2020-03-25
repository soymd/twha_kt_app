package com.example.twhaKtApp

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.util.Log
import java.util.*


class TwhaIntentService : IntentService {
    constructor(name: String?) : super(name) { // TODO 自動生成されたコンストラクター・スタブ
    }

    constructor() : super("TwhaIntentService") { // ActivityのstartService(intent);で呼び出されるコンストラクタはこちら
    }

    override fun onHandleIntent(intent: Intent?) { // 非同期処理を行うメソッド。タスクはonHandleIntentメソッド内で実行する
        val timer = Timer()
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    Log.i("hoge", "Task called.")
                }
            }, 1000, 1000
        )
        //10ミリ秒後に100ミリ秒間隔でタスク実行

        val handler = Handler()
//        handler.postDelayed(Runnable {
        Log.i("hoge", "onHandleIntent Start")

//        }, 2000)
    }

    override fun onDestroy() {
        Log.i("hoge", "destroyed")
        super.onDestroy()
    }
}