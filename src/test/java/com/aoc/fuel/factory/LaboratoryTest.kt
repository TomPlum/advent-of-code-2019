package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class LaboratoryTest {
    @Test
    fun exampleOne() {
        val reactionData = InputReader().readInputAsString("/reactions/example-1.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(165)
    }

    @Test
    fun exampleTwo() {
        val reactionData = InputReader().readInputAsString("/reactions/example-2.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(13312)
    }

    @Test
    fun exampleThree() {
        val reactionData = InputReader().readInputAsString("/reactions/example-3.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(180697)
    }

    @Test
    fun exampleFour() {
        val reactionData = InputReader().readInputAsString("/reactions/example-4.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(2210736)
    }
}