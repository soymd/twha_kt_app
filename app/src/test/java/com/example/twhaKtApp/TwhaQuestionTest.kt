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

        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
        makeYearRandomArrTestRandom()
//        makeYearRandomArrTest2(0)
        makeYearRandomArrTest2(1)
        makeYearRandomArrTest2(1)
        makeYearRandomArrTest2(1)
        makeYearRandomArrTest2(1)
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(-3900)
        makeYearRandomArrTest2(-3800)
        makeYearRandomArrTest2(-3700)
        makeYearRandomArrTest2(2019)
        makeYearRandomArrTest2(1919)
        makeYearRandomArrTest2(1819)
        makeYearRandomArrTest2(-3999)
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(-3999)
        makeYearRandomArrTest2(-4000)
        makeYearRandomArrTest2(2018)
        makeYearRandomArrTest2(2019)
        makeYearRandomArrTest2(2018)
        makeYearRandomArrTest2(2019)
    }

    fun makeYearRandomArrTestRandom() {
        var year: Int = 2000 - Random.nextInt(6000)
        makeYearRandomArrTest2(year)
    }

    fun makeYearRandomArrTest2(year: Int) {
        var difficulty = 1
        var yearArr = tQuestion.makeRandomYearArr(year, difficulty)
        println()
        print(year)
        print(": ")
        for (i in yearArr.indices) {
            print(yearArr[i])
            print(",")
            if (i < yearArr.count() - 1) {
                var num = yearArr[i]
                var num2 = yearArr[i + 1]
                assertTrue(num <= num2)//昇順になっているか
            }
        }
        assertTrue(yearArr.min()!! >= -4000)//最小値
        assertTrue(yearArr.max()!! <= 2019)//最大値
        assertTrue(yearArr.contains(year))//正答が含まれているか
        assertTrue(!yearArr.contains(0))//0が含まれていないか
        assertTrue(yearArr.count() == 4)//要素数が4つかどうか

        val randomRange = tQuestion.getRandomRangeByDifficulty(difficulty)
        val magnificationConst = 0.3
        val rangeMax = (randomRange * (1 + magnificationConst)).toInt()
        assertTrue(yearArr.last() - yearArr.first() <= rangeMax * yearArr.count() - 1)//乱数幅が正常か
    }

    @Test
    fun getRandomRangeByDifficultyTest() {
        getRandomRangeByDifficultyTest1(1, 120)
        getRandomRangeByDifficultyTest1(2, 70)
        getRandomRangeByDifficultyTest1(3, 40)
    }

    fun getRandomRangeByDifficultyTest1(difficulty: Int, expectedRandomRange: Int) {
        val actual = tQuestion.getRandomRangeByDifficulty(difficulty)
        val expected = expectedRandomRange
        assertEquals(actual, expected)
    }
}