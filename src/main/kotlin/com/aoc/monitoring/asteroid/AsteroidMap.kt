package com.aoc.monitoring.asteroid

import java.util.*

class AsteroidMap(mapData: List<String>) {
    private var map: Set<MapSector>

    init {
        map = mapData.mapIndexed { y, row ->
            row.chunked(1).mapIndexed { x, contents -> MapSector(contents, x, y) }
        }.flatten().toSet()
    }

    fun getRow(index: Int) = map.filter { it.y == index }.joinToString(separator = "") {it.contents}

    /**
     * @return A [Pair] of the optimal [MapSector] and the number of asteroids within
     * line of sight of the monitoring station if it was placed in this sector.
     */
    fun getOptimalAsteroidMappingStationSector(): Pair<MapSector, Int> {
        val asteroids = map.filter { it.hasAsteroid() }
        val angleMap: MutableMap<MapSector, Set<Double>> = mutableMapOf()
        asteroids.map { sourceAsteroid ->
            val targetAsteroids = asteroids.filter { it != sourceAsteroid }
            angleMap.put(sourceAsteroid, targetAsteroids.map { targetAsteroid ->
                sourceAsteroid.angleBetween(targetAsteroid)
            }.toSet())
        }

        val optimal = angleMap.maxBy { it.value.size }

        return Pair(optimal!!.key, optimal.value.size)
    }

    /**
     * The Instant Monitoring Station that was deployed on the asteroid of the optimal [MapSector] fires a
     * vaporising laser that starts from the vertical position and rotates clockwise until all the asteroids on
     * the map have been destroyed.
     * @return The [MapSector] containing the 200th asteroid to be destroyed
     */
    fun vaporiseAsteroidBelt(): MapSector {
        val asteroids = map.filter { it.hasAsteroid() }
        val laserStation = getOptimalAsteroidMappingStationSector().first
        val angleDistanceMap: SortedMap<Double, MutableSet<Pair<MapSector, Int>>> = sortedMapOf()
        asteroids.map { asteroid ->
            val angle = laserStation.angleBetween(asteroid)
            val distance = laserStation.distanceBetween(asteroid)
            angleDistanceMap.computeIfAbsent(angle) { sortedSetOf() }.add(Pair(asteroid, distance))
        }
        return MapSector("#", 0, 0)
    }

}