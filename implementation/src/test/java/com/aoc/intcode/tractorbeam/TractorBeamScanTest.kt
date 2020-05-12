package com.aoc.intcode.tractorbeam

import assertk.assertThat
import assertk.assertions.isEqualTo
import input.InputReader
import math.Point2D
import org.junit.jupiter.api.Test

class TractorBeamScanTest {
    @Test
    fun fromExistingScanData() {
        val data = InputReader().readInputAsString("/tractorbeam/example-beam.txt")
        val scan = TractorBeamScan.fromData(data.values)
        print(scan)
        assertThat(scan.getArea()).isEqualTo(1400)
    }

    @Test
    fun getPointsAffectedByBeam() {
        val scan = TractorBeamScan()
        scan.addTile(Point2D(0, 0), DroneState.propagating())
        scan.addTile(Point2D(0, 1), DroneState.stationary())
        scan.addTile(Point2D(0, 2), DroneState.stationary())
        scan.addTile(Point2D(0, 3), DroneState.propagating())
        assertThat(scan.getPointsAffectedByBeam()).isEqualTo(2)
    }

    @Test
    fun getArea() {
        val scan = TractorBeamScan()
        scan.addTile(Point2D(0, 0), DroneState.propagating())
        scan.addTile(Point2D(0, 1), DroneState.stationary())
        scan.addTile(Point2D(0, 2), DroneState.stationary())
        scan.addTile(Point2D(0, 3), DroneState.propagating())
        assertThat(scan.getArea()).isEqualTo(4)
    }

    @Test
    fun partTwoExample() {
        val data = InputReader().readInputAsString("/tractorbeam/example-beam.txt")
        val scan = TractorBeamScan.fromData(data.values)
        assertThat(scan.findSquareClosestToEmitter(10)).isEqualTo(250020L)
    }
}