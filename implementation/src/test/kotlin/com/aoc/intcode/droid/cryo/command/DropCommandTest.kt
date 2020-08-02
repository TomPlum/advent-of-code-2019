package com.aoc.intcode.droid.cryo.command

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.intcode.droid.cryo.droid.Item
import org.junit.jupiter.api.Test

class DropCommandTest {
    @Test
    fun encode() {
        val command = DropCommand("festive hat")
        val encoded = command.encode()
        assertThat(encoded).isEqualTo(listOf<Long>(100,114,111,112,32,102,101,115,116,105,118,101,32,104,97,116,10))
    }

    @Test
    fun getItem() {
        assertThat(DropCommand("protons").getItem()).isEqualTo(Item("protons"))
    }
}