package com.aoc.intcode.droid.cryo.command.system

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.cryo.command.system.HelpCommand
import org.junit.jupiter.api.Test

class HelpCommandTest {
    @Test
    fun getCommands() {
        assertThat(HelpCommand().getCommands()).isEqualTo(
                "You can move via 'north', 'east', 'south', 'west'\n" +
                "You can take an item with 'take <item name>'\n" +
                "You can drop an item with 'drop <item name>'\n" +
                "You can get a list of all the items current in the droid inventory with 'inv'\n"
        )
    }
}