package com.aoc.solutions

import com.aoc.input.Day
import com.aoc.input.InputReader
import com.aoc.password.PasswordUtility

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(4))
    println("Solution 1: " + PasswordUtility().calculatePossiblePasswordCombinations(input))
}