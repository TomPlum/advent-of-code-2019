package com.aoc.intcode.tractorbeam

import map.Map

class TractorBeamScan : Map<DroneState>() {
    /**
     * @return The number of points directly effected by the Tractor Beam
     */
    fun getPointsAffectedByBeam(): Int = filterTiles { it == DroneState.PROPAGATING }.count()

    /**
     * @return The total area of the scan, including every point scanned by the [DroneSystem]
     */
    fun getArea() = tileQuantity()
}