package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.TestInputReader
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class LaboratoryTest {

    @Test
    fun exampleZero() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-0.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(31)
    }

    @Test
    fun exampleOne() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-1.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(165)
    }

    @Test
    fun exampleTwo() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-2.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(13312)
    }

    @Test
    fun exampleThree() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-3.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(180697)
    }

    @Test
    fun exampleFour() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-4.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(2210736)
    }

    @Test
    fun solutionPartOne() {
        val reactionData = InputReader.read<String>(Day(14)).value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(1037742)
    }

    @Test
    fun partTwoExampleTwo() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-2.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(82_892_753)
    }

    @Test
    fun partTwoExampleThree() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-3.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(5_586_022)
    }

    @Test
    fun partTwoExampleFour() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-4.txt").value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(460_664)
    }

    @Test
    fun solutionPartTwo() {
        val reactionData = InputReader.read<String>(Day(14)).value
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(1572358)
    }
}