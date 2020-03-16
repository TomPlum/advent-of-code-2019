package com.aoc.password

import com.aoc.password.strategy.*

class PasswordUtility {
    fun calculatePossiblePasswordCombinations(range: String, strategy: PasswordValidationStrategy): Int {
        //TODO: Make Functional
        val inputs = range.split("-")
        var combinations = 0
        for (i in inputs[0].toInt()..inputs[1].toInt()) {
            if (Password(i.toString()).isValid(strategy)) combinations++
        }
        return combinations
    }

    /**
     * Returns true if the given [Password.value] has at least one pair of adjacent digits.
     * Pairs of digits that appear as part of a large group do no qualify.
     * - E.g 112345 return true. However, 111345 returns false as the 1s appear in a group of three.
     * @see RevisedValidation
     */
    fun hasAdjacentDigitPair(value: String): Boolean {
        val chars = value.toCharArray()
        var adjacent = 1
        val pairs = 0
        chars.forEachIndexed  { i, _ ->
            if (i != value.length - 1 && chars[i] == chars[i + 1]) {
                adjacent++
            } else {
                if (adjacent == 2) return true ; else adjacent = 1
            }
        }

        return pairs > 0
    }

    /**
     * Returns true if the given [Password.value] has at least one pair of adjacent digits.
     * The pair of adjacent digits can be part of a larger group, unlike [hasAdjacentDigitPair].
     * - E.g. Both 112345 and 111345 are valid and would return true.
     * @see InitialValidation
     */
    fun hasAdjacentDigits(value: String): Boolean {
        val chars = value.toCharArray()
        chars.forEachIndexed { i, _ ->
            if (i != value.length - 1 && chars[i] == chars[i + 1]) return true
        }
        return false
    }

    /**
     * Returns true if the given [Password.value] contains only ascending characters
     */
    fun hasOnlyAscendingCharacters(value: String) = value.asSequence().zipWithNext{i, j -> i <= j}.all{ it }
}