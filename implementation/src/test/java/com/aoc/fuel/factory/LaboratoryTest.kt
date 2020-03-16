package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.Day
import input.InputReader
import org.junit.jupiter.api.Test

class LaboratoryTest {

    @Test
    fun exampleZero() {
        val reactionData = InputReader().readInputAsString("/reactions/example-0.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(31)
    }

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

    @Test
    fun solutionPartOne() {
        val reactionData = InputReader().readInputString(Day.from(14)).values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.minimumOreToProduceOneFuel()).isEqualTo(1037742)
    }

    @Test
    fun partTwoExampleTwo() {
        val reactionData = InputReader().readInputAsString("/reactions/example-2.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(82_892_753)
    }

    @Test
    fun partTwoExampleThree() {
        val reactionData = InputReader().readInputAsString("/reactions/example-3.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(5_586_022)
    }

    @Test
    fun partTwoExampleFour() {
        val reactionData = InputReader().readInputAsString("/reactions/example-4.txt").values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(460_664)
    }

    @Test
    fun solutionPartTwo() {
        val reactionData = InputReader().readInputString(Day.from(14)).values
        val laboratory = Laboratory(NanoFactory(reactionData).produceReactionList())
        assertThat(laboratory.maximumFuelProducedFromOneTrillionOre()).isEqualTo(1572358)
    }
}