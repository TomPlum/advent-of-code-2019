package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.vault.new.DijkstraNode
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DijkstraNodeTest {
    @Nested
    inner class Equality {
        @Test
        fun equal() {
            val first = getStartingNode('A')
            first.updateDistance(DijkstraNode('C'), 10)
            val second = DijkstraNode('A')
            second.updateDistance(DijkstraNode('C'), 10)
            assertThat(first).isEqualTo(second)
        }

        @Test
        fun differentNames() {
            val first = getStartingNode('A')
            first.updateDistance(DijkstraNode('C'), 10)
            val second = DijkstraNode('B')
            second.updateDistance(DijkstraNode('C'), 10)
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentPaths() {
            val first = DijkstraNode('A')
            getStartingNode('@').updateDistance(first, 10)
            val second = DijkstraNode('A')
            getStartingNode('B').updateDistance(second, 10)
            assertThat(first).isNotEqualTo(second)
        }

        @Test
        fun differentNamesAndPaths() {
            val first = DijkstraNode('A')
            getStartingNode('@').updateDistance(first, 10)
            val second = DijkstraNode('C')
            getStartingNode('B').updateDistance(second, 10)
            assertThat(first).isNotEqualTo(second)
        }

        private fun getStartingNode(name: Char): DijkstraNode<Char> {
            val node = DijkstraNode(name)
            node.distance = 0
            return node
        }
    }
}