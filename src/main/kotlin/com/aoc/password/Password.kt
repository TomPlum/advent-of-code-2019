package com.aoc.password

class Password (val value: String) {
    fun isValid(): Boolean {
        if (value.isEmpty() || value.length != 6) return false

        if (!hasAdjacentDigits() || !hasOnlyAscendingCharacters()) return false

        return true
    }

    fun hasAdjacentDigits(): Boolean {
        val chars = value.toCharArray()
        chars.forEachIndexed { i, _ ->
            if (i != value.length - 1 && chars[i] == chars[i + 1]) return true
        }
        return false
    }

    fun hasOnlyAscendingCharacters(): Boolean {
        return value.asSequence().zipWithNext{i, j -> i <= j}.all{it}
    }
}