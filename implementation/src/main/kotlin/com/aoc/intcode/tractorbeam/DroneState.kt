package com.aoc.intcode.tractorbeam

enum class DroneState(private val glyph: String) {
    STATIONARY("."), PROPAGATING("#");

    override fun toString() = glyph
}