package com.aoc.password

class PasswordUtility {
    fun conformsToCriteria(input: String): Boolean {
        if (input.isEmpty() || input.length != 6) return false
        return true
    }

    fun hasExactlyTwoAdjacentDigits(input: String): Boolean {
        return false
    }
}