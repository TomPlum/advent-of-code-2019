package com.aoc.panel

class Wire constructor(input: String) {
    val segments: List<WireSegment> = input.split(",").map { WireSegment(it.substring(0, 1), it.substring(1).toInt()) }
}