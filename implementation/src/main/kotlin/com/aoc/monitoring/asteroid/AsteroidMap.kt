package com.aoc.monitoring.asteroid

import com.aoc.math.Point2D

class AsteroidMap(mapData: List<String>) {
    private var asteroids: Set<MapSector>

    init {
        asteroids = mapData.asSequence().mapIndexed { y, row ->
            row.chunked(1).mapIndexed { x, contents -> MapSector(contents, Point2D(x, y)) }
        }.flatten().filter { it.hasAsteroid() }.toSet()
    }

    /**
     * The elves want to build an|d Asteroid Monitoring Station on an asteroid nearby in the given [AsteroidMap].
     * The optimal [MapSector] is the one in which has direct line of sight of the most asteroids.
     *
     * @return A [Pair] of the optimal [MapSector] and the number of asteroids within
     * line of sight of the monitoring station if it was placed in this sector.
     */
    fun getOptimalAsteroidMappingStationSector(): Pair<MapSector, Int> {
        val angleMap: MutableMap<MapSector, Set<Double>> = mutableMapOf()
        asteroids.map { sourceAsteroid ->
            val targetAsteroids = asteroids.filter { it != sourceAsteroid }
            angleMap.put(sourceAsteroid, targetAsteroids.map { targetAsteroid ->
                sourceAsteroid.angleBetween(targetAsteroid)
            }.toSet())
        }

        val optimal = angleMap.maxByOrNull { it.value.size }

        return Pair(optimal!!.key, optimal.value.size)
    }

    /**
     * The Instant Monitoring Station that was deployed on the asteroid of the optimal [MapSector] fires a
     * vaporising laser that starts from the vertical position and rotates clockwise until all the asteroids on
     * the map have been destroyed.
     * @param n The nth asteroid to be vaporised as per the aforementioned order where 1 is the first.
     * @return The [MapSector] containing the [n]th asteroid to be destroyed.
     */
    fun vaporiseAsteroidBelt(n: Int): MapSector {
        val laserStation = getOptimalAsteroidMappingStationSector().first
        val angleSectorMap: MutableMap<Double, MutableList<MapSector>> = asteroids
                .filter { it != laserStation }
                .groupBy { laserStation.angleBetween(it) }
                .mapValues { entry -> entry.value.sortedBy { laserStation.distanceBetween(it) }.toMutableList() }
                .toSortedMap()

        val vaporised = mutableListOf<MapSector>()
        while (angleSectorMap.isNotEmpty()) {
            angleSectorMap.forEach { vaporised.add(it.value.removeAt(0)) }
            angleSectorMap.filterKeys { angleSectorMap.getValue(it).isEmpty() }.forEach { angleSectorMap.remove(it.key) }
        }

        return vaporised[n - 1]
    }

    /**
     * To win the bet with the elves on which asteroid be the 200th to be vaporised, this function returns the value
     * when multiplying the asteroids x-ordinate by 100 and adding it it's y-ordinate.
     * @return ([MapSector.position] x * 100) + [MapSector.position] y
     * @see AsteroidMap.vaporiseAsteroidBelt
     */
    fun winBetWithElves(): Int {
        val asteroid = vaporiseAsteroidBelt(200)
        return (asteroid.position.x * 100) + asteroid.position.y
    }

}