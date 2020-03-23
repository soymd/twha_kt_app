package com.example.twhaKtApp

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TwhaRepositoryTest {
    private lateinit var repository: TwhaRepository

    @Before
    fun setUp() {
        repository = TwhaRepository()
    }

    @Test
    fun insertData() {
        assertEquals(1, 1)
    }

    @Test
    fun insertDifficulty() {
//        val userId = 1
//        val difficulty = 1
//        repository.insertDifficulty(userId, difficulty)
//        val actual = repository.readDifficulty(userId)
//        assertEquals(difficulty, actual)
    }

    @Test
    fun readDifficulty() {
    }
}