package com.aoc.input

abstract class GenericInput<T>(val value: List<T>) {
    fun asSingleString() = value.joinToString(separator = "")
}