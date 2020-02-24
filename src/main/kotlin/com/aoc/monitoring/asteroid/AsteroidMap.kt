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
     * line of sight of a monitoring station if it was placed in this sector.
     */
    fun getOptimalAsteroidMappingStationSector(): Pair<MapSector, Int> {
        val asteroids = map.filter { it.hasAsteroid() }
        asteroids.map { sourceSector ->
            asteroids.map {
            }
        }

        return Pair(MapSector("#", 0, 0), 0)
    }

    private fun getSector(x: Int, y: Int): MapSector = map.find { it.x == x && it.y == y }!!
}