package com.example.twhaKtApp

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RpsUtilTest {
    private lateinit var rps: RpsUtil
    @Before
    fun setUp() {
        rps = RpsUtil()
    }

    fun p2(p1: Rps, p2: Rps, expected: RpsResult) {
        print("p1: $p1, p2: $p2, expected: $expected, ")
        val actual = rps.rps(p1, p2);
        println("actual: $actual")
        assertEquals(expected, actual)
    }

    @Test
    fun p2Test() {
        p2(Rps.ROCK, Rps.ROCK, RpsResult.DRAW)
        p2(Rps.ROCK, Rps.SCISSOR, RpsResult.WIN)
        p2(Rps.ROCK, Rps.PAPER, RpsResult.LOSE)
        p2(Rps.SCISSOR, Rps.ROCK, RpsResult.LOSE)
        p2(Rps.SCISSOR, Rps.SCISSOR, RpsResult.DRAW)
        p2(Rps.SCISSOR, Rps.PAPER, RpsResult.WIN)
        p2(Rps.PAPER, Rps.ROCK, RpsResult.WIN)
        p2(Rps.PAPER, Rps.SCISSOR, RpsResult.LOSE)
        p2(Rps.PAPER, Rps.PAPER, RpsResult.DRAW)
    }

    //3人ver
    fun p3(p1: Rps, p2: Rps, p3: Rps, expected: RpsResult) {
        print("p1: $p1, p2: $p2, p3: $p3, expected: $expected, ")
        val actual = rps.rps(p1, p2, p3);
        println("actual: $actual")
        assertEquals(expected, actual)
    }

    @Test
    fun p3Test() {
        p3(Rps.ROCK, Rps.ROCK, Rps.SCISSOR, RpsResult.WIN)
        p3(Rps.ROCK, Rps.ROCK, Rps.PAPER, RpsResult.LOSE)
        p3(Rps.ROCK, Rps.SCISSOR, Rps.ROCK, RpsResult.WIN)
        p3(Rps.ROCK, Rps.SCISSOR, Rps.SCISSOR, RpsResult.WIN)
        p3(Rps.ROCK, Rps.SCISSOR, Rps.PAPER, RpsResult.DRAW)
        p3(Rps.ROCK, Rps.PAPER, Rps.ROCK, RpsResult.LOSE)
        p3(Rps.ROCK, Rps.PAPER, Rps.SCISSOR, RpsResult.DRAW)
        p3(Rps.ROCK, Rps.PAPER, Rps.PAPER, RpsResult.LOSE)
        p3(Rps.SCISSOR, Rps.ROCK, Rps.ROCK, RpsResult.LOSE)
        p3(Rps.SCISSOR, Rps.ROCK, Rps.SCISSOR, RpsResult.LOSE)
        p3(Rps.SCISSOR, Rps.ROCK, Rps.PAPER, RpsResult.DRAW)
        p3(Rps.SCISSOR, Rps.SCISSOR, Rps.ROCK, RpsResult.LOSE)
        p3(Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, RpsResult.DRAW)
        p3(Rps.SCISSOR, Rps.SCISSOR, Rps.PAPER, RpsResult.WIN)
        p3(Rps.SCISSOR, Rps.PAPER, Rps.ROCK, RpsResult.DRAW)
        p3(Rps.SCISSOR, Rps.PAPER, Rps.SCISSOR, RpsResult.WIN)
        p3(Rps.SCISSOR, Rps.PAPER, Rps.PAPER, RpsResult.WIN)
        p3(Rps.PAPER, Rps.ROCK, Rps.ROCK, RpsResult.WIN)
        p3(Rps.PAPER, Rps.ROCK, Rps.SCISSOR, RpsResult.DRAW)
        p3(Rps.PAPER, Rps.ROCK, Rps.PAPER, RpsResult.WIN)
        p3(Rps.PAPER, Rps.SCISSOR, Rps.ROCK, RpsResult.DRAW)
        p3(Rps.PAPER, Rps.SCISSOR, Rps.SCISSOR, RpsResult.LOSE)
        p3(Rps.PAPER, Rps.SCISSOR, Rps.PAPER, RpsResult.LOSE)
        p3(Rps.PAPER, Rps.PAPER, Rps.ROCK, RpsResult.WIN)
        p3(Rps.PAPER, Rps.PAPER, Rps.SCISSOR, RpsResult.LOSE)
        p3(Rps.PAPER, Rps.PAPER, Rps.PAPER, RpsResult.DRAW)
    }

    @Test
    fun rpsMultiTest() {
//        rpsMulti(Rps.PAPER, Rps.PAPER, Rps.PAPER, Rps.SCISSOR, RpsResult.LOSE)
    }

    fun rpsMulti(vararg hands: Rps, expected: RpsResult) {
        val actual = rps.count(*hands)
        assertEquals(expected, actual)

    }

    @Test
    fun hoge() {
        println(0 shl 0)
        println(2 shl 0)
        println(0 shl 1)
        println(2 shl 1)
        println(0 shl 2)
        println(2 shl 2)
        println(0 shl 3)
        println(2 shl 3)
        println(0 shl 4)
        println(2 shl 4)
        println(0 shl 5)
        println(2 shl 5)
    }

    infix fun shl(x: Int): Int {
        val y = x + 4
        return y * 2
    }
}