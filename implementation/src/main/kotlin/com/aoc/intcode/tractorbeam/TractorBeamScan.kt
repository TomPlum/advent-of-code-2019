package com.aoc.intcode.tractorbeam

import map.Map

class TractorBeamScan : Map<DroneState>() {
    fun getPointsAffectedByBeam(): Int = filterTiles { it == DroneState.PROPAGATING }.count()
}