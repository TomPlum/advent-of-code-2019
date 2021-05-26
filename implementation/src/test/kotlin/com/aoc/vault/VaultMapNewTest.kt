package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.input.TestInputReader
import com.aoc.vault.new.VaultMapNew
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class VaultMapNewTest {
    @Test
    fun exampleOne() {
        val input = TestInputReader().readInputAsString("/vault/example-1.txt").value
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(8)
    }

    @Test
    fun exampleTwo() {
        val input = TestInputReader().readInputAsString("/vault/example-2.txt").value
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(86)
    }

    @Test
    fun exampleThree() {
        val input = TestInputReader().readInputAsString("/vault/example-3.txt").value
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(132)
    }

    @Test
    @Disabled("Runs indefinitely")
    fun exampleFour() {
        val input = TestInputReader().readInputAsString("/vault/example-4.txt").value
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(136)
    }

    @Test
    fun exampleFive() {
        val input = TestInputReader().readInputAsString("/vault/example-5.txt").value
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(81)
    }

    @Test
    fun solutionPartOne() {
        val input = InputReader.read<String>(Day(18)).value;
        val steps = VaultMapNew(input).collectKeys()
        assertThat(steps).isEqualTo(5262)
    }
}