package com.aoc.intcode.droid.cryo.command.types

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.droid.cryo.command.TestItemCommand
import com.aoc.intcode.droid.cryo.droid.Item
import org.junit.jupiter.api.Test

class ItemCommandTest {
    @Test
    fun getItem() {
        assertThat(TestItemCommand("wreath").getItem()).isEqualTo(Item("wreath"))
    }
}