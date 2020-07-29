package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import com.aoc.intcode.droid.Room
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DroidOutputTest {
    @Nested
    inner class ParseRoomName {
        @Test
        fun validOutputString() {
            val output = TestInputReader().readInputAsString("/droid/cryo/example-droid-output.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parseRoom()).isEqualTo(Room("Kitchen"))
        }
    }
}