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
    fun p4Test() {
        p4(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.ROCK, RpsResult.DRAW)
        p4(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, RpsResult.WIN)
        p4(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.PAPER, RpsResult.LOSE)
        p4(Rps.ROCK, Rps.ROCK, Rps.SCISSOR, Rps.PAPER, RpsResult.DRAW)
        p4(Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, RpsResult.DRAW)
        p4(Rps.SCISSOR, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, RpsResult.LOSE)
        p4(Rps.SCISSOR, Rps.ROCK, Rps.ROCK, Rps.PAPER, RpsResult.DRAW)
        p4(Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, Rps.PAPER, RpsResult.WIN)
        p4(Rps.PAPER, Rps.PAPER, Rps.PAPER, Rps.PAPER, RpsResult.DRAW)
        p4(Rps.PAPER, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, RpsResult.DRAW)
        p4(Rps.PAPER, Rps.ROCK, Rps.ROCK, Rps.PAPER, RpsResult.WIN)
        p4(Rps.PAPER, Rps.SCISSOR, Rps.SCISSOR, Rps.PAPER, RpsResult.LOSE)
    }

    fun p4(
        p1: Rps,
        p2: Rps,
        p3: Rps,
        p4: Rps,
        expected: RpsResult
    ) {
        val actual = rps.rpsVararg(p1, p2, p3, p4)
        assertEquals(expected, actual)
    }

    @Test
    fun multiTest() {
        var actual: RpsResult
        actual = rps.rpsVararg(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.ROCK)
        assertEquals(RpsResult.DRAW, actual)
        actual = rps.rpsVararg(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.SCISSOR)
        assertEquals(RpsResult.WIN, actual)
        actual = rps.rpsVararg(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.PAPER)
        assertEquals(RpsResult.LOSE, actual)
        actual = rps.rpsVararg(Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, Rps.PAPER)
        assertEquals(RpsResult.DRAW, actual)

        actual = rps.rpsVararg(Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR)
        assertEquals(RpsResult.DRAW, actual)
        actual = rps.rpsVararg(Rps.SCISSOR, Rps.PAPER, Rps.PAPER, Rps.SCISSOR, Rps.PAPER)
        assertEquals(RpsResult.WIN, actual)
        actual = rps.rpsVararg(Rps.SCISSOR, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, Rps.ROCK)
        assertEquals(RpsResult.LOSE, actual)
        actual = rps.rpsVararg(Rps.SCISSOR, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, Rps.PAPER)
        assertEquals(RpsResult.DRAW, actual)

        actual = rps.rpsVararg(Rps.PAPER, Rps.PAPER, Rps.PAPER, Rps.PAPER, Rps.PAPER)
        assertEquals(RpsResult.DRAW, actual)
        actual = rps.rpsVararg(Rps.PAPER, Rps.ROCK, Rps.ROCK, Rps.ROCK, Rps.PAPER)
        assertEquals(RpsResult.WIN, actual)
        actual = rps.rpsVararg(Rps.PAPER, Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR, Rps.SCISSOR)
        assertEquals(RpsResult.LOSE, actual)
        actual = rps.rpsVararg(Rps.PAPER, Rps.ROCK, Rps.ROCK, Rps.SCISSOR, Rps.PAPER)
        assertEquals(RpsResult.DRAW, actual)

    }

    @Test
    fun hoge() {
        val hoge = A(3)
        print(hoge.y)
    }

    open class A(x: Int) {
        open val y: Int = x
    }

    interface B {}

    val ab: A = object : A(1), B {
        override val y = 15
    }

}
