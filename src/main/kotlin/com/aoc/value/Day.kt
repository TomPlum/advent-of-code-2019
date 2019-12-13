package com.aoc.value

class Day private constructor(val value: Int) {
    companion object {
        fun from(value: Int): Day {
            return Day(value)
        }
    }
}