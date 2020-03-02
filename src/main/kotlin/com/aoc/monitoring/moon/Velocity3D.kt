package com.aoc.monitoring.moon

data class Velocity3D(var x: Int, var y: Int, var z: Int) {
    override fun toString() = "vel=<x=$x, y=$y, z=$z>"
}