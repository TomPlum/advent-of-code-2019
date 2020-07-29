package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import com.aoc.math.Direction.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DroidOutputTest {
    @Nested
    inner class ParseRoomName {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/example-droid-output.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().name).isEqualTo("Kitchen")
        }
    }

    @Nested
    inner class ParseDoorLocations {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/example-droid-output.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().doors).isEqualTo(listOf(UP, DOWN, LEFT))
        }
    }

    @Nested
    inner class ParseItems {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/example-droid-output.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().items).isEqualTo(listOf(Item("escape pod")))
        }
    }
}