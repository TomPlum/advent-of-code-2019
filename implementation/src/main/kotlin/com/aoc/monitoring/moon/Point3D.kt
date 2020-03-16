package com.aoc.monitoring.moon

data class Point3D(val x: Int, val y: Int, val z: Int) {
    override fun toString() = "pos=<x=$x, y=$y, z=$z>"
}