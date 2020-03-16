package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.password.PasswordUtility

fun main() {
    val inputReader = SolutionUtility().inputReader
    val utility = PasswordUtility()
    val input = inputReader.readInputAsSingleString(Day.from(4))
    println("Solution 1: " + utility.calculatePossiblePasswordCombinations(input))
}