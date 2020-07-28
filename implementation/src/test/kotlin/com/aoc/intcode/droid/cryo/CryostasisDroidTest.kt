package com.aoc.intcode.droid.cryo

import com.aoc.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class CryostasisDroidTest {
    @Test
    fun boot() {
        val input = InputReader.read<String>(Day(25)).asSingleString()
        CryostasisDroid(input)
    }
}