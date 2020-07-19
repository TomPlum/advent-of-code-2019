package com.aoc.monitoring.eris

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEqualTo
import com.aoc.TestInputReader
import com.aoc.input.InputReader
import com.aoc.math.Point2D
import org.junit.jupiter.api.Test

class PlanarErisPlanetLayoutTest {

    @Test
    fun getDyingBugsExampleOne() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt")
        val scan = PlanarErisPlanetLayout(scanData.values)
        assertThat(scan.getDyingBugs()).isEqualTo(listOf(Point2D(4,0), Point2D(3,2), Point2D(2,3), Point2D(0,4)))
    }

    @Test
    fun getInfestedTilesExampleOne() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt")
        val scan = PlanarErisPlanetLayout(scanData.values)
        assertThat(scan.getInfestedTiles()).isEqualTo(listOf(Point2D(0,0), Point2D(3,0), Point2D(1,1), Point2D(2,1),
                Point2D(1,2), Point2D(2,2), Point2D(0,3), Point2D(1,3), Point2D(3,3), Point2D(4,3), Point2D(1,4),
                Point2D(2,4)))
    }

    @Test
    fun getDyingBugsExampleTwo() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-2.txt")
        val scan = PlanarErisPlanetLayout(scanData.values)
        assertThat(scan.getDyingBugs()).isEqualTo(listOf(Point2D(0,1), Point2D(1,1), Point2D(2,1), Point2D(3,1),
                                                         Point2D(0,2), Point2D(1,2), Point2D(2,2), Point2D(0,3),
                                                         Point2D(1,3), Point2D(4,3), Point2D(1,4)))
    }

    @Test
    fun getInfestedTilesExampleTwo() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-2.txt")
        val scan = PlanarErisPlanetLayout(scanData.values)
        assertThat(scan.getInfestedTiles()).isEqualTo(listOf(Point2D(1,0), Point2D(2,0), Point2D(4,0), Point2D(4,1),
                                                             Point2D(0,4), Point2D(3,4), Point2D(4,4)))
    }

    @Test
    fun getBioDiversityRating() {
        val scanData = TestInputReader().readInputAsString("/eris/biodiversity-example.txt")
        val scan = PlanarErisPlanetLayout(scanData.values)
        assertThat(scan.getBioDiversityRating()).isEqualTo(2129920)
    }

    @Test
    fun equalityTestPositive() {
        val scanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").values
        assertThat(PlanarErisPlanetLayout(scanData)).isEqualTo(PlanarErisPlanetLayout(scanData))
    }

    @Test
    fun equalityTestNegative() {
        val firstScanData = TestInputReader().readInputAsString("/eris/example-scan-1.txt").values
        val firstScan = PlanarErisPlanetLayout(firstScanData)

        val secondScanData = TestInputReader().readInputAsString("/eris/biodiversity-example.txt").values
        val secondScan = PlanarErisPlanetLayout(secondScanData)

        assertThat(firstScan).isNotEqualTo(secondScan)
    }

}
