package com.aoc.password

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.Day
import com.aoc.solutions.SolutionUtility
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PasswordUtilityTest {
    private val utility = PasswordUtility()
    private val puzzleInput = SolutionUtility().inputReader.readInputAsSingleString(Day.from(4))

    @Test
    @DisplayName("Given the input from Day 4 Part 1, when calculating the number of valid password combinations, then it should return 466")
    fun solutionOne() {
        val solution = utility.calculatePossiblePasswordCombinations(puzzleInput)
        assertThat(solution).isEqualTo(466)
    }
}