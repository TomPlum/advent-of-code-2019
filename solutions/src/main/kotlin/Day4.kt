package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.password.PasswordUtility

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(4))
    println("Solution 1: " + PasswordUtility().calculatePossiblePasswordCombinations(input))
}