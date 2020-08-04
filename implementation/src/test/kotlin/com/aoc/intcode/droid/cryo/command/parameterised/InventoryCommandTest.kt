package com.aoc.intcode.droid.cryo.command.parameterised

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.cryo.command.parameterised.InventoryCommand
import org.junit.jupiter.api.Test

class InventoryCommandTest {
    @Test
    fun encode() {
        assertThat(InventoryCommand().encode()).isEqualTo(listOf<Long>(105, 110, 118, 10))
    }
}