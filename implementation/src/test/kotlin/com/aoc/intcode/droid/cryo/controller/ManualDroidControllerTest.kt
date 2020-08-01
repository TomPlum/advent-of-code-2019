package com.aoc.intcode.droid.cryo.controller

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class ManualDroidControllerTest {
    @Test
    fun solutionPartOne() {
        val instructions = InputReader.read<String>(Day(25)).asSingleString()
        val password = ManualDroidController(instructions).findPassword()
        assertThat(password.value).isEqualTo("196872")
    }
}