package com.aoc.password

class PasswordUtility {
    fun calculatePossiblePasswordCombinations(value: String): Int {
        val inputs = value.split("-")
        var combinations = 0
        for (i in inputs[0].toInt()..inputs[1].toInt()) {
            if (Password(i.toString()).isValid()) combinations++
        }
        return combinations
    }
}