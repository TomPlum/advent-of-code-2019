package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.math.Point3D
import org.junit.jupiter.api.Test

class RecursiveErisPlanetLayoutTest {
    @Test
    fun adjacencyTest() {
        val layout = RecursiveErisPlanetLayout(listOf())
        val adjacentPositions = layout.getAdjacentPositions(Point3D(0, 0, 0))
        assertThat(adjacentPositions).isEqualTo(listOf(Point3D(0,1,0), Point3D(1,0,0), Point3D(2,1,-1), Point3D(1,2,-1)))
    }

    @Test
    fun adjacencyCentreTest() {
        val layout = RecursiveErisPlanetLayout(listOf())
        val adjacentPositions = layout.getAdjacentPositions(Point3D(2, 1, 0))
        assertThat(adjacentPositions).isEqualTo(listOf(Point3D(0,0,1), Point3D(1,0,1), Point3D(2,0,1), Point3D(3,0,1), Point3D(4,0,1), Point3D(3,1,0),  Point3D(2,0,0), Point3D(1,1,0)))
    }
}