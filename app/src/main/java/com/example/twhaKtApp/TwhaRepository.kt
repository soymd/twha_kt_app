package com.example.twhaKtApp

import android.content.ContentValues
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class TwhaRepository {

    public fun insertData(
        context: Context, userId: Int, difficulty: Int, year: Int, option1: Int, option2: Int
        , option3: Int, option4: Int, selected: Int, correct: Int
    ) {
        val dbHelper = AssetDatabaseOpenHelper(context);
        val database = dbHelper.openDatabase()
        val tableName = "answer"
        try {
            val dbHelper = AssetDatabaseOpenHelper(context);
            val database = dbHelper.openDatabase()
            val values = ContentValues()

            val date = Date()
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val now: String = format.format(date)
            values.put("user_id", userId)
            values.put("date", now)
            values.put("year", year)
            values.put("option1", option1)
            values.put("option2", option2)
            values.put("option3", option3)
            values.put("option4", option4)
            values.put("selected", selected)
            values.put("difficulty", difficulty)
            values.put("correct", correct)

            database.insertOrThrow(tableName, null, values)
        } catch (exception: Exception) {
            Log.e("db", exception.toString())
        } finally {
            database.close()
        }
    }

    public fun insertDifficulty(context: Context, userId: Int, difficulty: Int) {
        val dbHelper = AssetDatabaseOpenHelper(context);
        val database = dbHelper.openDatabase()
        val tableName = "user"
        val columnName = "difficulty"
        try {
            val values = ContentValues()
            values.put(columnName, difficulty)
            database.update(tableName, values, "user_id = $userId", null)
        } catch (exception: Exception) {
            Log.e("db", exception.toString())
        } finally {
            database.close()
        }

    }

    public fun readDifficulty(context: Context, userId: Int): Int {
        var difficulty: Int = 1
        val dbHelper = AssetDatabaseOpenHelper(context);
        val database = dbHelper.openDatabase()
        val tableName = "user"
        // 検索するuser_idを指定
        val selectionArgs = arrayOf<String>(userId.toString())
        val cursor = database.query(
            tableName, arrayOf("difficulty"),
            "user_id = ?",
            selectionArgs,
            null,
            null,
            null
        )
        try {
            cursor.moveToFirst()
            cursor.getColumnName(0)
            difficulty = cursor.getInt(0)

        } catch (exception: Exception) {
            Log.e("db", exception.toString())
        } finally {
            cursor.close()
            database.close()
        }
        return difficulty
    }
}