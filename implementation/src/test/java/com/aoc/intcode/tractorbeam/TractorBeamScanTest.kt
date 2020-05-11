package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import math.Point2D
import org.junit.jupiter.api.Test

class TractorBeamScanTest {
    @Test
    fun getPointsAffectedByBeam() {
        val scan = TractorBeamScan()
        scan.addTile(Point2D(0, 0), DroneState.PROPAGATING)
        scan.addTile(Point2D(0, 1), DroneState.STATIONARY)
        scan.addTile(Point2D(0, 2), DroneState.STATIONARY)
        scan.addTile(Point2D(0, 3), DroneState.PROPAGATING)
        assertThat(scan.getPointsAffectedByBeam()).isEqualTo(2)
    }

    @Test
    fun getArea() {
        val scan = TractorBeamScan()
        scan.addTile(Point2D(0, 0), DroneState.PROPAGATING)
        scan.addTile(Point2D(0, 1), DroneState.STATIONARY)
        scan.addTile(Point2D(0, 2), DroneState.STATIONARY)
        scan.addTile(Point2D(0, 3), DroneState.PROPAGATING)
        assertThat(scan.getArea()).isEqualTo(4)
    }
}