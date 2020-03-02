package com.aoc.monitoring.moon

data class Point3D(var x: Int, var y: Int, var z: Int) {
    override fun toString() = "pos=<x=$x, y=$y, z=$z>"
}