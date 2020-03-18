package com.example.twhaKtApp

import kotlin.random.Random

class TwhaQuestion {
    fun determineCentury(year: Int): Int {
        if (year > 0) {
            return (year + 99) / 100
        } else {
            return (year - 99) / 100
        }
    }

    fun centuryToString(century: Int): String {
        val bcPrefix = "前"
        val suffix = "世紀"
        if (century > 0) {
            return century.toString() + suffix
        } else {
            return bcPrefix + (century * -1).toString() + suffix
        }
    }

    fun makeRandomYearArr(year: Int): IntArray {
        var maxRandomYear: Int
        var minRandomYear: Int
        var randomRangeMax = 400
        var randomRangeMin = 300

//        do {
        maxRandomYear = year + Random.nextInt(randomRangeMin, randomRangeMax)
        minRandomYear = year - Random.nextInt(randomRangeMin, randomRangeMax)

        if (minRandomYear < -4000) minRandomYear = -4000
        if (maxRandomYear > 2019) maxRandomYear = 2019

        //-4000より小さいか、2019より大きい場合はやり直す
//        } while (minRandomYear < -4000 || maxRandomYear > 2019)

        // 正答を格納するインデックスを決定
        val randomIndex = Random.nextInt(3)
        val yearArr = intArrayOf(0, 0, 0, 0)
        when (randomIndex) {
            0 -> {
                yearArr[0] = year
                yearArr[3] = forceRandom(year, maxRandomYear)
                yearArr[1] = forceRandom(yearArr[0], yearArr[3])
                yearArr[2] = forceRandom(yearArr[0], yearArr[3])
            }
            1, 2 -> {
                yearArr[0] = minRandomYear + forceRandom(randomRangeMin / 2, randomRangeMax / 2)
                yearArr[3] = maxRandomYear - forceRandom(randomRangeMin / 2, randomRangeMax / 2)
                yearArr[1] = year
                yearArr[2] = forceRandom(yearArr[0], yearArr[3])
            }
            3 -> {
                yearArr[0] = forceRandom(minRandomYear, year)
                yearArr[3] = year
                yearArr[1] = forceRandom(yearArr[0], yearArr[3])
                yearArr[2] = forceRandom(yearArr[0], yearArr[3])
            }
        }
        // 配列を昇順にソートして返却
        return yearArr.sorted().toIntArray()
    }

    private fun forceRandom(year1: Int, year2: Int): Int {
        if (year1 > year2) {
            return Random.nextInt(year2, year1)
        } else if (year1 < year2) {
            return Random.nextInt(year1, year2)
        }
        return year1
    }
}