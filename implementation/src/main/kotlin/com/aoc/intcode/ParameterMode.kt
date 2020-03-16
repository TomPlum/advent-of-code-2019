package com.aoc.intcode

/**
 * 0 = POSITION MODE : The value at the index of the parameter value
 * 1 = IMMEDIATE MODE : The value is the parameter value
 * 2 = RELATIVE MODE : The value is at the index given by the sum of the relative base and the parameter
 */
enum class ParameterMode(val code: Int) {
    POSITION(0), IMMEDIATE(1), RELATIVE(2)
}