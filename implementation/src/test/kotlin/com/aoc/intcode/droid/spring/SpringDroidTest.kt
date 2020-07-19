package com.aoc.intcode.droid.spring

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.spring.survey.RunningStrategy
import com.aoc.intcode.droid.spring.survey.WalkingStrategy
import org.junit.jupiter.api.Test

class SpringDroidTest {
    @Test
    fun solutionPartOne() {
        val input = InputReader.read<String>(Day.from(21)).asSingleString()
        val droid = SpringDroid(input)
        assertThat(droid.surveyHull(WalkingStrategy()).damage).isEqualTo(19350258)
    }

    @Test
    fun solutionPartTwo() {
        val input = InputReader.read<String>(Day.from(21)).asSingleString()
        val droid = SpringDroid(input)
        assertThat(droid.surveyHull(RunningStrategy()).damage).isEqualTo(1142627861)
    }
}