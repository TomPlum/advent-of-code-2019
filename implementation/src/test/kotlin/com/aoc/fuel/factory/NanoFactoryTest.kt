package com.aoc.fuel.factory

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.TestInputReader
import com.aoc.fuel.factory.components.Chemical
import com.aoc.fuel.factory.components.Fuel
import com.aoc.fuel.factory.components.Ore
import org.junit.jupiter.api.Test

class NanoFactoryTest {
    @Test
    fun factoryParsesReactionDataCorrectly() {
        val reactionData = TestInputReader().readInputAsString("/reactions/example-0.txt").value
        val factory = NanoFactory(reactionData)
        val reactions = factory.produceReactionList()
        assertThat(reactions[0]).isEqualTo(Reaction(setOf(Ore(10)), Chemical("A", 10)))
        assertThat(reactions[1]).isEqualTo(Reaction(setOf(Ore(1)), Chemical("B", 1)))
        assertThat(reactions[2]).isEqualTo(Reaction(setOf(Chemical("A", 7), Chemical("B", 1)), Chemical("C", 1)))
        assertThat(reactions[3]).isEqualTo(Reaction(setOf(Chemical("A", 7), Chemical("C", 1)), Chemical("D", 1)))
        assertThat(reactions[4]).isEqualTo(Reaction(setOf(Chemical("A", 7), Chemical("D", 1)), Chemical("E", 1)))
        assertThat(reactions[5]).isEqualTo(Reaction(setOf(Chemical("A", 7), Chemical("E", 1)), Fuel(1)))
    }
}