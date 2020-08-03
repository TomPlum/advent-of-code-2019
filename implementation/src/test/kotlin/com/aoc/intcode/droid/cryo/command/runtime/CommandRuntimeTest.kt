package com.aoc.intcode.droid.cryo.command.runtime

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.isInstanceOf
import com.aoc.Day
import com.aoc.droid.cryo.command.runtime.TestCommandReader
import com.aoc.droid.cryo.droid.TestDroidLogger
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import com.aoc.intcode.droid.cryo.droid.DroidLogger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandRuntimeTest {

    private val input = InputReader.read<String>(Day(25)).asSingleString()
    private val runtime = CommandRuntime(input)
    private val reader = TestCommandReader()
    private val logger = TestDroidLogger()

    @BeforeEach
    fun setUp() {
        runtime.reader = reader
        runtime.logger = logger
    }

    @Test
    fun startAndQuit() {
        reader.inputCommand("quit")
        runtime.start()
        assertThat(logger.logs).containsOnly("Booting Cryostasis Droid runtime environment...\n")
    }

    @Test
    fun help() {
        reader.inputCommand("help")
        reader.inputCommand("quit")
        runtime.start()
        assertThat(logger.logs).contains(HelpCommand().getCommands())
    }

    @Test
    fun validCommandIsIssuedToDroid() {
        reader.inputCommand("east")
        reader.inputCommand("quit")
        runtime.start()
    }

    @Test
    fun invalidCommandLogsErrorMessage() {
        reader.inputCommand("ojhdfls")
        reader.inputCommand("quit")
        runtime.start()
        assertThat(logger.logs).contains("Invalid command: ojhdfls")
    }

    @Test
    fun invalidCommandLogsCommandHelpMessage() {
        reader.inputCommand("ojhdfls")
        reader.inputCommand("quit")
        runtime.start()
        assertThat(logger.logs).contains("Use command 'help' to view a complete list of commands\n")
    }

    @Test
    fun cliDefaultsToDroidCommandReader() {
        assertThat(CommandRuntime(input).reader).isInstanceOf(DroidCommandReader::class)
    }

    @Test
    fun loggerDefaultsToDroidLogger() {
        assertThat(CommandRuntime(input).logger).isInstanceOf(DroidLogger::class)
    }
}