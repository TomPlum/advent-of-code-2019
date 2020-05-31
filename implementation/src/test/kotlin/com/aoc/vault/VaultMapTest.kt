package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class VaultMapTest {

    @Test
    fun exampleOne() {
        val input = InputReader().readInputAsString("/vault/example-1.txt").values
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(8)
    }

    @Test
    fun exampleTwo() {
        val input = InputReader().readInputAsString("/vault/example-2.txt").values
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(86)
    }

    @Test
    fun exampleThree() {
        val input = InputReader().readInputAsString("/vault/example-3.txt").values
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(132)
    }

    @Test
    @Disabled("Until fixed on feature/day18")
    fun exampleFour() {
        val input = InputReader().readInputAsString("/vault/example-4.txt").values
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(136)
    }

    @Test
    fun exampleFive() {
        val input = InputReader().readInputAsString("/vault/example-5.txt").values
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(81)
    }

}