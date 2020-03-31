package com.example.twhaKtApp

class RpsUtil {
    fun rps(p1: Rps, p2: Rps): RpsResult {
        val result = p1.num - p2.num + 3
        return when (result % 3) {
            1 -> RpsResult.LOSE
            2 -> RpsResult.WIN
            else -> RpsResult.DRAW
        }
    }

    fun rps(p1: Rps, p2: Rps, p3: Rps): RpsResult {
        if (p2 == p3) return rps(p1, p2)
        if (p1 == p2) return rps(p1, p3)
        if (p1 == p3) return rps(p1, p2)
        return RpsResult.DRAW
    }

    fun rpsVararg(vararg hands: Rps): RpsResult {
        val p1 = hands[0]
        var p2: Rps = p1
        var p3: Rps = p1

        val opponent = hands.toMutableList()
        opponent.removeAt(0)
        print(p1.toString())
        print(opponent.toString())

        when (p1) {
            Rps.ROCK -> {
                if (opponent.contains(Rps.SCISSOR)) p2 = Rps.SCISSOR
                if (opponent.contains(Rps.PAPER)) p3 = Rps.PAPER
            }
            Rps.SCISSOR -> {
                if (opponent.contains(Rps.ROCK)) p2 = Rps.ROCK
                if (opponent.contains(Rps.PAPER)) p3 = Rps.PAPER
            }
            Rps.PAPER -> {
                if (opponent.contains(Rps.ROCK)) p2 = Rps.ROCK
                if (opponent.contains(Rps.SCISSOR)) p3 = Rps.SCISSOR
            }
        }
        val result = rps(p1, p2, p3)
        println(result)
        return result
    }
}