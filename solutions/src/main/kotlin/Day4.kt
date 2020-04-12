package com.aoc.solutions

import input.Day
import input.InputReader
import com.aoc.password.PasswordUtility
import com.aoc.password.strategy.InitialValidation
import com.aoc.password.strategy.RevisedValidation

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(4))
    val utility = PasswordUtility()
    println("Solution Part 1: ${utility.calculatePossiblePasswordCombinations(input, InitialValidation())}")
    println("Solution Part 2: ${utility.calculatePossiblePasswordCombinations(input, RevisedValidation())}")
}