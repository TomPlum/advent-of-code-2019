package com.aoc.password

class PasswordUtility {
    fun calculatePossiblePasswordCombinations(input: String): Int {
        val inputs = input.split("-")
        var combinations = 0
        for (i in inputs[0].toInt()..inputs[1].toInt()) {
            if (conformsToCriteria(i.toString())) combinations++
        }
        return combinations
    }

    fun conformsToCriteria(input: String): Boolean {
        if (input.isEmpty() || input.length != 6) return false

        if (!hasAdjacentDigits(input) || !hasOnlyAscendingCharacters(input)) return false

        return true
    }

    fun hasAdjacentDigits(input: String): Boolean {
        val chars = input.toCharArray()
        chars.forEachIndexed { i, _ ->
            if (i != input.length - 1 && chars[i] == chars[i + 1]) return true
        }
        return false
    }

    fun hasOnlyAscendingCharacters(input: String): Boolean {
        return input.asSequence().zipWithNext{i, j -> i <= j}.all{it}
    }
}