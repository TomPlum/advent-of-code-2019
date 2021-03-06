package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.password.PasswordUtility
import com.aoc.password.strategy.InitialValidation
import com.aoc.password.strategy.RevisedValidation

fun main() {
    val input = InputReader.read<String>(Day(4)).asSingleString()
    val utility = PasswordUtility()
    println("Solution Part 1: ${utility.getValidPasswordCombination(input, InitialValidation())}")
    println("Solution Part 2: ${utility.getValidPasswordCombination(input, RevisedValidation())}")
}