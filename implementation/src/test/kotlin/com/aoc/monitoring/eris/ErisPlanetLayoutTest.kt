package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class ErisPlanetLayoutTest {

    @Test
    fun getDyingBugs() {
        val scanData = InputReader().readInputAsString("/eris/example-scan.txt")
        val scan = ErisPlanetLayout(scanData.values)
        assertThat(scan.getDyingBugs()).isEqualTo(listOf(Point2D(4,0), Point2D(3,2), Point2D(2,3), Point2D(0,4)))
    }

    @Test
    fun getInfestedTiles() {
        val scanData = InputReader().readInputAsString("/eris/example-scan.txt")
        val scan = ErisPlanetLayout(scanData.values)
        assertThat(scan.getInfestedTiles()).isEqualTo(listOf(Point2D(0,0), Point2D(3,0), Point2D(1,1), Point2D(2,1),
                Point2D(1,2), Point2D(2,2), Point2D(0,3), Point2D(1,3), Point2D(3,3), Point2D(4,3), Point2D(1,4),
                Point2D(2,4)))
    }

    @Test
    fun getBioDiversityRating() {
        val scanData = InputReader().readInputAsString("/eris/biodiversity-example.txt")
        val scan = ErisPlanetLayout(scanData.values)
        assertThat(scan.getBioDiversityRating()).isEqualTo(2129920)
    }

}
