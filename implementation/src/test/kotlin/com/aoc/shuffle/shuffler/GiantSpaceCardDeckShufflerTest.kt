package com.aoc.shuffle.shuffler

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test
import java.math.BigInteger.ONE

class GiantSpaceCardDeckShufflerTest {
    @Test
    fun inversePartOne() {
        val input = InputReader.read<String>(Day(22)).value
        val shuffler = GiantSpaceCardDeckShuffler(10007.toBigInteger(), input)
        assertThat(shuffler.shuffle(ONE).getCard(2604.toBigInteger())).isEqualTo(2019.toBigInteger())
    }

    @Test
    fun solutionPartTwo() {
        val input = InputReader.read<String>(Day(22)).value
        val deckSize = 119315717514047.toBigInteger()
        val times = 101741582076661.toBigInteger()
        val position = 2020.toBigInteger()
        val shuffler = GiantSpaceCardDeckShuffler(deckSize, input)
        assertThat(shuffler.shuffle(times).getCard(position)).isEqualTo(79608410258462.toBigInteger())
    }
}