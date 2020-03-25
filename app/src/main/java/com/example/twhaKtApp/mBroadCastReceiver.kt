package com.example.twhaKtApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class mBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) { //送信されたブロードキャストを受信してトーストで表示
        Toast.makeText(context, "受信しました", Toast.LENGTH_SHORT).show()
    }
}