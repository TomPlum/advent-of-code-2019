package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.survey.WalkingStrategy
import org.junit.jupiter.api.Test

class SpringDroidTest {
    @Test
    fun solutionPartOne() {
        val input = InputReader().readInputAsSingleString(Day.from(21))
        val droid = SpringDroid(input)
        assertThat(droid.surveyHull(WalkingStrategy()).damage).isEqualTo(19350258)
    }
}