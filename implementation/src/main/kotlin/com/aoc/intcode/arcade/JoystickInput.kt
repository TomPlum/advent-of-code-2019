package com.aoc.intcode.arcade

enum class JoystickInput(val direction: Int) {
    LEFT(-1),
    NEUTRAL(0),
    RIGHT(1)
}