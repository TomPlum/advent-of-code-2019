package com.aoc.intcode

/**
 * 0 = POSITION MODE
 * 1 = IMMEDIATE MODE
 */
enum class ParameterMode(val code: Int) {
    POSITION(0), IMMEDIATE(1), RELATIVE(2)
}