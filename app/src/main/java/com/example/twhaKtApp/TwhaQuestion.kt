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

    fun centuryToString(century: Int): String {
        val bcPrefix = "前"
        val suffix = "世紀"
        return if (century > 0) {
            century.toString() + suffix
        } else {
            bcPrefix + (century * -1).toString() + suffix
        }
    }

    fun makeRandomYearArr(year: Int, difficulty: Int): MutableList<Int> {
        val maxYear = 2019
        val minYear = -4000
        val randomRange = getRandomRangeByDifficulty(difficulty)

        val magnificationConst = 0.3
        val rangeMax = (randomRange * (1 + magnificationConst)).toInt()
        val rangeMin = (randomRange * (1 - magnificationConst)).toInt()

        // 正答を格納するインデックスを決定
        val randomIndex = Random.nextInt(3)
        var tempArr = mutableListOf<Int>(0, 0, 0, year, 0, 0, 0)
        tempArr[2] = tempArr[3] - Random.nextInt(rangeMin, rangeMax)
        tempArr[1] = tempArr[2] - Random.nextInt(rangeMin, rangeMax)
        tempArr[0] = tempArr[1] - Random.nextInt(rangeMin, rangeMax)
        tempArr[4] = tempArr[3] + Random.nextInt(rangeMin, rangeMax)
        tempArr[5] = tempArr[4] + Random.nextInt(rangeMin, rangeMax)
        tempArr[6] = tempArr[5] + Random.nextInt(rangeMin, rangeMax)

        //乱数で回答候補に0が生じてしまった場合、1に変換
        if (tempArr.contains(0)) {
            tempArr[tempArr.indexOf(0)] = 1
        }

        val yearArr = mutableListOf<Int>()
        for (year in tempArr) {
            // minYear以上maxYear以下の場合はそのまま追加
            if (year in minYear..maxYear) {
                yearArr.add(year)
            } else {
                // minYearより小さい or maxYearより大きいの場合は0を追加
                yearArr.add(0)
            }
        }

        when (yearArr.count { it == 0 }) {
            3 -> {
                yearArr.removeAll { it == 0 }
                return yearArr.sorted().toMutableList()
            }
            2 -> {
                yearArr.removeAll { it == 0 }
                return if (randomIndex < 2) {
                    yearArr.slice(0..3).sorted().toMutableList()
                } else {
                    yearArr.slice(1..4).sorted().toMutableList()
                }
            }
            1 -> {
                val tmp = yearArr.slice(randomIndex..randomIndex + 3).sorted().toMutableList()
                if (tmp.first() == 0) {
                    tmp[0] = yearArr[randomIndex - 1]
                }
                if (tmp.last() == 0) {
                    tmp[tmp.count() - 1] = yearArr[randomIndex + 4]
                }
                return tmp.toMutableList()
            }
            0 -> {
                return yearArr.slice(randomIndex..randomIndex + 3).sorted().toMutableList()
            }
        }
        // 配列を昇順にソートして返却
        return yearArr.sorted().toMutableList()
    }

    private fun getRandomYear(year1: Int, year2: Int): Int {
        if (year1 > year2) {
            return Random.nextInt(year2, year1)
        } else if (year1 < year2) {
            return Random.nextInt(year1, year2)
        }
        return year1
        
    }

    fun getRandomRangeByDifficulty(difficulty: Int): Int {
        return when (difficulty) {
            1 -> return 120
            2 -> return 70
            3 -> return 40
            else -> 0
        }
    }
}