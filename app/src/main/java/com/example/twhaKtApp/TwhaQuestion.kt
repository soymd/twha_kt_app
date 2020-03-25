package com.example.twhaKtApp

import kotlin.random.Random

class TwhaQuestion {
    fun determineCentury(year: Int): Int {
        return if (year > 0) {
            (year + 99) / 100
        } else {
            (year - 99) / 100
        }
    }

    fun randomYear(): Int {
        return when (Random.nextInt(100)) {
            //出題する年の乱数を調整
            in 0..2 -> Random.nextInt(-4000, -1001)
            in 3..10 -> Random.nextInt(-1000, -1)
            //紀元0年は返さないようにする
            else -> Random.nextInt(1, 2019)
        }
    }

    fun centuryToString(century: Int): String {
        val bcPrefix = "前"
        val suffix = "世紀"
        return if (century > 0) {
            century.toString() + suffix
        } else {
            bcPrefix + (century * -1).toString() + suffix
        }
    }

    fun makeRandomYearList(year: Int, difficulty: Int): MutableList<Int> {

        val randomRange = getRandomRangeByDifficulty(difficulty)
        val MAGNIFICATION_CONST = 0.3 //乱数の振れ幅
        val rangeMax = (randomRange * (1 + MAGNIFICATION_CONST)).toInt()
        val rangeMin = (randomRange * (1 - MAGNIFICATION_CONST)).toInt()
        val tempYearList = makeYearList(year, rangeMin, rangeMax)

        // 正答を格納するインデックスを決定
        val randomIndex = Random.nextInt(3)

        //乱数で回答候補に0が生じてしまった場合、1に変換
        if (tempYearList.contains(0)) {
            tempYearList[tempYearList.indexOf(0)] = 1
        }

        val yearList = checkRange(tempYearList)
        return trimYearList(yearList, randomIndex)
    }

    fun makeYearList(year: Int, rangeMin: Int, rangeMax: Int): MutableList<Int> {

        val list = mutableListOf(0, 0, 0, year, 0, 0, 0)
        list[2] = list[3] - Random.nextInt(rangeMin, rangeMax)
        list[1] = list[2] - Random.nextInt(rangeMin, rangeMax)
        list[0] = list[1] - Random.nextInt(rangeMin, rangeMax)
        list[4] = list[3] + Random.nextInt(rangeMin, rangeMax)
        list[5] = list[4] + Random.nextInt(rangeMin, rangeMax)
        list[6] = list[5] + Random.nextInt(rangeMin, rangeMax)

        return list
    }

    fun checkRange(yearList: MutableList<Int>): MutableList<Int> {
        val maxYear = 2019
        val minYear = -4000
        val tempList = mutableListOf<Int>()

        for (year in yearList) {
            // minYear以上maxYear以下の場合はそのまま追加
            if (year in minYear..maxYear) {
                tempList.add(year)
            } else {
                // minYearより小さい or maxYearより大きいの場合は0を追加
                tempList.add(0)
            }
        }
        return tempList
    }

    private fun trimYearList(yearList: MutableList<Int>, randomIndex: Int): MutableList<Int> =
        when (yearList.count { it == 0 }) {
            3 -> {
                yearList.removeAll { it == 0 }
                yearList.sorted().toMutableList()
            }
            2 -> {
                yearList.removeAll { it == 0 }
                if (randomIndex < 2) {
                    yearList.slice(0..3).sorted().toMutableList()
                } else {
                    yearList.slice(1..4).sorted().toMutableList()
                }
            }
            1 -> {
                val tmp = yearList.slice(randomIndex..randomIndex + 3).sorted().toMutableList()
                if (tmp.first() == 0) {
                    tmp[0] = yearList[randomIndex - 1]
                }
                if (tmp.last() == 0) {
                    tmp[tmp.count() - 1] = yearList[randomIndex + 4]
                }
                tmp.toMutableList()
            }
            else -> {
                yearList.slice(randomIndex..randomIndex + 3).sorted().toMutableList()
            }
        }

    private fun getRandomYear(year1: Int, year2: Int): Int {
        if (year1 > year2) {
            return Random.nextInt(year2, year1)
        } else if (year1 < year2) {
            return Random.nextInt(year1, year2)
        }
        return year1
    }

    fun getRandomRangeByDifficulty(difficulty: Int): Int =
        when (difficulty) {
            1 -> 120
            2 -> 70
            3 -> 40
            else -> 0
        }
}