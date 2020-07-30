package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class CommandParserTest {
    private val parser = CommandParser()

    @Nested
    inner class ParseMovementCommand {
        @Test
        fun north() {
            assertThat(parser.parse("north")).isEqualTo(MovementCommand("north"))
        }

        @Test
        fun east() {
            assertThat(parser.parse("east")).isEqualTo(MovementCommand("east"))
        }

        @Test
        fun south() {
            assertThat(parser.parse("south")).isEqualTo(MovementCommand("south"))
        }

        @Test
        fun west() {
            assertThat(parser.parse("west")).isEqualTo(MovementCommand("west"))
        }
    }

    @Nested
    inner class ParseTakeCommand {
        @Test
        fun take() {
            assertThat(parser.parse("take some item")).isEqualTo(TakeCommand("some item"))
        }

        @Test
        fun takeWhenItemNameHasTrailingSpace() {
            assertThat(parser.parse("take syringe ")).isEqualTo(TakeCommand("syringe"))
        }
    }

    @Nested
    inner class ParseDropCommand {
        @Test
        fun drop() {
            assertThat(parser.parse("drop some item")).isEqualTo(DropCommand("some item"))
        }

        @Test
        fun dropWhenItemNameHasTrailingSpace() {
            assertThat(parser.parse("drop syringe ")).isEqualTo(DropCommand("syringe"))
        }
    }

    @Nested
    inner class ErrorScenarios {
        @Test
        fun nullInstruction() {
            val e = assertThrows<InvalidCommand> { parser.parse(null) }
            assertThat(e.message).isEqualTo("Invalid command: NULL")
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = ["jump", "taka", "dropp item"])
        fun unrecognisedCommand(command: String) {
            val e = assertThrows<InvalidCommand> { parser.parse(command) }
            assertThat(e.message).isEqualTo("Invalid command: $command")
        }
    }
}