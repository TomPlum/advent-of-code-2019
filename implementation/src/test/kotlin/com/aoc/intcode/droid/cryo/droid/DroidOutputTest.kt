package com.aoc.intcode.droid.cryo.droid

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.aoc.input.TestInputReader
import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.security.SecurityAnalysis
import com.aoc.math.Direction.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DroidOutputTest {
    private val reader = TestInputReader()

    @Nested
    inner class ParseRoomName {
        @Test
        fun validOutputString() {
            val output = reader.readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().name).isEqualTo("Kitchen")
        }
    }

    @Nested
    inner class ParseDoorLocations {
        @Test
        fun validOutputString() {
            val output = reader.readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().doors).isEqualTo(listOf(UP, DOWN, LEFT))
        }

        @Test
        fun invalidDirectionalString() {
            val output = reader.readInputAsString("/droid/cryo/droid-output-invalid-direction.txt").asSingleString()
            val e = assertThrows<IllegalArgumentException> { DroidOutput(output).parse() }
            assertThat(e.message).isEqualTo("Invalid Direction: down")
        }
    }

    @Nested
    inner class ParseItems {
        @Test
        fun validOutputString() {
            val output = reader.readInputAsString("/droid/cryo/droid-output-kitchen.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().items).isEqualTo(listOf(Item("escape pod")))
        }

        @Test
        fun outputHasNoItems() {
            val output = reader.readInputAsString("/droid/cryo/droid-output-hull-breach.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parse().items).isEmpty()
        }
    }

    @Nested
    inner class ParsePressureSensitiveFloor {
        @Test
        fun tooLight() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-too-light.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.TOO_LIGHT)
        }

        @Test
        fun tooHeavy() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-too-heavy.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.TOO_HEAVY)
        }

        @Test
        fun correctWeight() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-complete.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePressureSensitiveFloor()).isEqualTo(SecurityAnalysis.VALID)
        }

        @Test
        fun outputTextFromSantaButHasInvalidSecurityAnalysis() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-invalid-analysis.txt").asSingleString()
            val e = assertThrows<IllegalStateException> { DroidOutput(output).parsePressureSensitiveFloor() }
            assertThat(e.message).isEqualTo("Invalid Security Analysis:\n$output")
        }

        @Test
        fun outputTextInvalid() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-invalid-weight.txt").asSingleString()
            val e = assertThrows<IllegalStateException> { DroidOutput(output).parsePressureSensitiveFloor() }
            assertThat(e.message).isEqualTo("Droid is not on the Pressure-Sensitive Floor")
        }
    }

    @Nested
    inner class ParsePassword {
        @Test
        fun validOutput() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-valid.txt").asSingleString()
            val droidOutput = DroidOutput(output)
            assertThat(droidOutput.parsePassword()).isEqualTo(AirlockPassword("196872"))
        }

        @Test
        fun outputTextInvalid() {
            val output = reader.readInputAsString("/droid/cryo/pressure-sensitive-floor-invalid-password.txt").asSingleString()
            val e = assertThrows<IllegalStateException> { DroidOutput(output).parsePassword() }
            assertThat(e.message).isEqualTo("Droid output does not contain a password!:\n$output")
        }
    }
}