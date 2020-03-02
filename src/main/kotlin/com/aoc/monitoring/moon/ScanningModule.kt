package com.aoc.monitoring.moon

/**
 * Currently tracks the position of Io, Europa, Ganymede and Callisto.
 */
class ScanningModule {
    fun scanLocalSectorForMoons(positionalData: List<String>): Set<Moon> = positionalData
            .map { line ->
                val coords = line.removeSurrounding("<", ">").split(", ").map { it.substring(2).toInt() }
                Moon(Point3D(coords[0], coords[1], coords[2]), Velocity3D(0, 0, 0))
            }.toSet()
}