package com.aoc.input.types

abstract class Input<T>(val value: List<T>) {
    fun asSingleString() = value.joinToString(separator = "\n")
}