package com.aoc.password

class Password (val value: String) {
    fun isValid(): Boolean {
        if (value.isEmpty() || value.length != 6) return false

        if (!hasAdjacentDigitPair() || !hasOnlyAscendingCharacters()) return false

        return true
    }

    /**
     * Return true if the [Password] has at least one pair of adjacent digits.
     * @sample 112345 return true. 111345 returns false.
     */
    fun hasAdjacentDigitPair(): Boolean {
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

    fun hasOnlyAscendingCharacters(): Boolean = value.asSequence().zipWithNext{i, j -> i <= j}.all{it}

}