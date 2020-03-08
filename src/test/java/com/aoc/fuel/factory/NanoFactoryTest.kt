package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.fuel.factory.components.Chemical
import com.aoc.fuel.factory.components.Fuel
import com.aoc.fuel.factory.components.Ore
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class NanoFactoryTest {
    @Test
    fun factoryParsesReactionDataCorrectly() {
        val reactionData = InputReader().readInputAsString("/reactions/example-0.txt").values
        val factory = NanoFactory(reactionData)
        val reactions = factory.produceReactionList()
        assertThat(reactions[0]).isEqualTo(Reaction(setOf(Ore(10.0)), Chemical("A", 10.0)))
        assertThat(reactions[1]).isEqualTo(Reaction(setOf(Ore(1.0)), Chemical("B", 1.0)))
        assertThat(reactions[2]).isEqualTo(Reaction(setOf(Chemical("A", 7.0), Chemical("B", 1.0)), Chemical("C", 1.0)))
        assertThat(reactions[3]).isEqualTo(Reaction(setOf(Chemical("A", 7.0), Chemical("C", 1.0)), Chemical("D", 1.0)))
        assertThat(reactions[4]).isEqualTo(Reaction(setOf(Chemical("A", 7.0), Chemical("D", 1.0)), Chemical("E", 1.0)))
        assertThat(reactions[5]).isEqualTo(Reaction(setOf(Chemical("A", 7.0), Chemical("E", 1.0)), Fuel(1.0)))
    }
}