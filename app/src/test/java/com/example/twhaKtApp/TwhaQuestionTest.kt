package com.example.twhaKtApp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class TwhaQuestionTest {
    private lateinit var tQuestion: TwhaQuestion

    @Before
    fun setUp() {
        tQuestion = TwhaQuestion()
    }

    @Test
    fun determineCenturyTest() {
        val yearArr = intArrayOf(-4000, -3999, -1, 0, 1, 100, 101, 2001)
        val centArr = arrayOf(-40, -40, -1, 0, 1, 1, 2, 21)
        for (i in yearArr.indices) {
            val actual = tQuestion.determineCentury(yearArr[i])
            val expected = centArr[i]
            assertEquals(actual, expected)
        }
    }

    @Test
    fun centuryToStringTest() {
        val yearArr = intArrayOf(-40, -39, 1)
        val centArr = arrayOf("前40世紀", "前39世紀", "1世紀")
        for (i in yearArr.indices) {
            val actual = tQuestion.centuryToString(yearArr[i])
            val expected = centArr[i]
            assertEquals(actual, expected)
        }
    }

    @Test
    fun makeYearRandomArrTest() {
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest1()
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(2019)
        makeYearRandomArrTest2(-3999)
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(-3999)
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(2018)
        makeYearRandomArrTest2(2019)
        makeYearRandomArrTest2(2018)
        makeYearRandomArrTest2(2019)
    }

    fun makeYearRandomArrTest1() {
        var year: Int = 2000 - Random.nextInt(6000)
        var yearArr = tQuestion.makeRandomYearArr(year)
        println()
        print(year)
        print(" → ")
        for (i in yearArr.indices) {
            print(yearArr[i])
            print(" ")
            if (i < 3) {
                var num = yearArr[i]
                var num2 = yearArr[i + 1]
                assertTrue(num <= num2)
            }
        }
        assertTrue(yearArr[0] >= -4000)
        assertTrue(yearArr[3] <= 2019)
//        assertTrue(yearArr[3] - yearArr[0] <= 400)
    }

    fun makeYearRandomArrTest2(year: Int) {
        var yearArr = tQuestion.makeRandomYearArr(year)
        println()
        print(year)
        print(" → ")
        for (i in yearArr.indices) {
            print(yearArr[i])
            print(" ")
            if (i < 3) {
                var num = yearArr[i]
                var num2 = yearArr[i + 1]
                assertTrue(num <= num2)
            }
        }
        assertTrue(yearArr[0] >= -4000)
        assertTrue(yearArr[3] <= 2019)
        assertTrue(yearArr[3] - yearArr[0] <= 400)
    }
}