package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class VaultMapTest {

    @Test
    fun exampleOne() {
        val input = TestInputReader().readInputAsString("/vault/example-1.txt").value
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(8)
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader().readInputAsString("/vault/example-2.txt").value
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(86)
    }

    @Test
    fun exampleThree() {
        val input = TestInputReader().readInputAsString("/vault/example-3.txt").value
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(132)
    }

    @Test
    @Disabled
    fun exampleFour() {
        val input = TestInputReader().readInputAsString("/vault/example-4.txt").value
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(136)
    }

    @Test
    fun exampleFive() {
        val input = TestInputReader().readInputAsString("/vault/example-5.txt").value
        val steps = VaultMap(input).collectKeys()
        assertThat(steps).isEqualTo(81)
    }

}