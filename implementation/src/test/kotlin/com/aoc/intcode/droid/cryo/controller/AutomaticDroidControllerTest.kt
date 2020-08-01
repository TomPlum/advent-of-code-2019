package com.aoc.intcode.droid.cryo.controller

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class AutomaticDroidControllerTest {
    @Test
    fun solutionPartOne() {
        val instructions = InputReader.read<String>(Day(25)).asSingleString()
        val password = AutomaticDroidController(instructions).findPassword()
        assertThat(password.value).isEqualTo("196872")
    }
}