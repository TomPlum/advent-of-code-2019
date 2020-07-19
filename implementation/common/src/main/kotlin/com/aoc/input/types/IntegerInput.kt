package com.aoc.input.types

class IntegerInput(input: List<String>) : Input<Int>(input.map { it.toInt() })