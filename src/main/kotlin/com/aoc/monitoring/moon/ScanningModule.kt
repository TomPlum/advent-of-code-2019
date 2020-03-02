package com.aoc.monitoring.moon

import java.util.*

/**
 * Currently tracks the position of Io, Europa, Ganymede and Callisto.
 */
class ScanningModule {
    private val names = LinkedList(listOf("Io", "Europa", "Ganymede", "Callisto"))

    fun scanLocalSectorForMoons(positionalData: List<String>): Set<Moon> = positionalData
            .map { line ->
                val coords = line.removeSurrounding("<", ">").split(", ").map { it.substring(2).toInt() }
                Moon(names.poll(), Point3D(coords[0], coords[1], coords[2]))
            }.toSet()
}