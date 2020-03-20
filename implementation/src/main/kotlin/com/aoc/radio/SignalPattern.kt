package com.aoc.radio

import java.util.*

data class SignalPattern(private val basePattern: List<Int>) {
    private val values = LinkedList(basePattern.drop(1))

    fun getValue(): Int {
        if (values.size == 0) values.addAll(basePattern)
        return values.pollFirst()
    }

}