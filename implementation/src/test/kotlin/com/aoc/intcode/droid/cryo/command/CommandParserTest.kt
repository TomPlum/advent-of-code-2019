package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.aoc.intcode.droid.cryo.command.item.DropCommand
import com.aoc.intcode.droid.cryo.command.item.TakeCommand
import com.aoc.intcode.droid.cryo.command.parameterised.InventoryCommand
import com.aoc.intcode.droid.cryo.command.parameterised.MovementCommand
import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import com.aoc.intcode.droid.cryo.command.system.QuitCommand
import com.aoc.intcode.droid.cryo.exceptions.InvalidCommand
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
        fun takeSingleWordItem() {
            assertThat(parser.parse("take wreath")).isEqualTo(TakeCommand("wreath"))
        }

        @Test
        fun takeItemWithSpace() {
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
    inner class ParseHelpCommand {
        @Test
        fun help() {
            assertThat(parser.parse("help")).isInstanceOf(HelpCommand::class)
        }

        @Test
        fun helpWithLeadingSpace() {
            assertThat(parser.parse(" help")).isInstanceOf(HelpCommand::class)
        }

        @Test
        fun helpWithTrailingSpace() {
            assertThat(parser.parse("help ")).isInstanceOf(HelpCommand::class)
        }
    }

    @Nested
    inner class ParseQuitCommand {
        @Test
        fun quit() {
            assertThat(parser.parse("quit")).isInstanceOf(QuitCommand::class)
        }

        @Test
        fun quitWithLeadingSpace() {
            assertThat(parser.parse(" quit")).isInstanceOf(QuitCommand::class)
        }

        @Test
        fun quitWithTrailingSpace() {
            assertThat(parser.parse("quit ")).isInstanceOf(QuitCommand::class)
        }
    }

    @Nested
    inner class ParseInventoryCommand {
        @Test
        fun inv() {
            assertThat(parser.parse("inv")).isInstanceOf(InventoryCommand::class)
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