package com.aoc.radio

data class Signal(val sequence: List<Int>) {
    override fun toString() = sequence.joinToString(separator = "")
}