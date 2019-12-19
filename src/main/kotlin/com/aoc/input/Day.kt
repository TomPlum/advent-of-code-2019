package com.aoc.input

class Day private constructor(val value: Int) {
    companion object {
        fun from(value: Int): Day {
            return Day(value)
        }
    }
}