package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import org.junit.jupiter.api.Test

class SpringDroidTest {
    @Test
    fun solutionPartOne() {
        val input = InputReader().readInputAsSingleString(Day.from(21))
        val droid = SpringDroid(input)
        assertThat(droid.surveyHull().value).isEqualTo(19350258)
    }
}