package com.aoc.cards

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
}