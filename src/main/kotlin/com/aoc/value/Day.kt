package com.aoc.value

class Day private constructor(private val value: Int) {

    companion object {
        fun from(value: Int): Day {
            return Day(value)
        }
    }

    fun getValue() : Int {
        return value
    }

}