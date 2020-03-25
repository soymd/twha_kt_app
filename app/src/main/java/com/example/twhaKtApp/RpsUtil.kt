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

    fun count(vararg hands: Rps) {
        val p1 = hands[0]
        val opponent = hands.toMutableList().removeAt(0)
//        val hands = mutableListOf<Rps>()
//        if (hand.contains(Rps.ROCK)) hands.add(Rps.ROCK)
//        if (hand.contains(Rps.SCISSOR)) hands.add(Rps.SCISSOR)
//        if (hand.contains(Rps.PAPER)) hands.add(Rps.PAPER)
        println(hands)
//        val rock = hand.count { it == Rps.ROCK }
//        val scissor = hand.count { it == Rps.SCISSOR }
//        val paper = hand.count { it == Rps.PAPER }
//        println("$rock, $scissor, $paper")
    }
}