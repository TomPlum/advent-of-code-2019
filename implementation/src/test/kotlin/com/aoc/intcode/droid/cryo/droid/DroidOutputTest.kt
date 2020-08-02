package com.aoc.intcode.droid.cryo.droid

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import com.aoc.intcode.droid.cryo.security.SecurityAnalysis
import com.aoc.math.Direction.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DroidOutputTest {
    @Nested
    inner class ParseRoomName {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().name).isEqualTo("Kitchen")
        }
    }

    @Nested
    inner class ParseDoorLocations {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().doors).isEqualTo(listOf(UP, DOWN, LEFT))
        }
    }

    @Nested
    inner class ParseItems {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().items).isEqualTo(listOf(Item("escape pod")))
        }

        @Test
        fun outputHasNoItems() {
            val output = TestInputReader().readInputAsString("/droid/cryo/droid-output-hull-breach.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().items).isEmpty()
        }
    }

    @Nested
    inner class ParsePressureSensitiveFloor {
        @Test
        fun tooLight() {
            val output = TestInputReader().readInputAsString("/droid/cryo/pressure-sensitive-floor-too-light.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.TOO_LIGHT)
        }

        @Test
        fun tooHeavy() {
            val output = TestInputReader().readInputAsString("/droid/cryo/pressure-sensitive-floor-too-heavy.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.TOO_HEAVY)
        }

        @Test
        fun correctWeight() {
            val output = TestInputReader().readInputAsString("/droid/cryo/pressure-sensitive-floor-complete.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.VALID)
        }
    }
}