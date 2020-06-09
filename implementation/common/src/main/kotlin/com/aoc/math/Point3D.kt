package com.aoc.math

import kotlin.math.abs

data class Point3D(val x: Int, val y: Int, val z: Int) {
    fun planarAdjacentPoints(): List<Point3D> = listOf(Point3D(x, y + 1, z), Point3D(x + 1, y, z), Point3D(x, y - 1, z), Point3D(x - 1, y, z))

    fun isPlanarAdjacentTo(that: Point3D): Boolean = this != that && abs(x - that.x) <= 1 && abs(y - that.y) <= 1 && z == that.z

    fun distanceFromAxisY(): Int = abs(0 - y)

    fun distanceFromAxisX(): Int = abs(0 - x)

    override fun toString() = "($x, $y, $z)"
}