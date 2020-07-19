package com.aoc.input

abstract class Input<T>(val value: List<T>) {
    fun asSingleString() = value.joinToString(separator = "")
}