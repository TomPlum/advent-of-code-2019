package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.vault.new.DijkstraGraph
import com.aoc.vault.new.DijkstraNode
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DijkstraGraphTest {
    @Nested
    inner class CalculatePathDistance {
        @Test
        fun exampleFiveThreeKeys() {
            val graph = DijkstraGraph<Char>()
            val entrance = DijkstraNode('@')
            graph.addNode(entrance)
            val a = DijkstraNode('a')
            graph.addNode(a)
            val d = DijkstraNode('d')
            graph.addNode(d)
            val e = DijkstraNode('e')
            graph.addNode(e)

            graph.getNode('@').addDestination(a, 15, emptySet(), emptySet())
            graph.getNode('a').addDestination(d, 14, emptySet(), emptySet())
            graph.getNode('d').addDestination(e, 4, emptySet(), emptySet())

            assertThat(graph.calculatePathDistance(listOf('@', 'a', 'd', 'e'))).isEqualTo(33)
        }
    }
}