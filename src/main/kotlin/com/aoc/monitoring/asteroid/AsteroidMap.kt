package com.aoc.monitoring.asteroid

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

}