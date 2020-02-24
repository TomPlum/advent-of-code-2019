package com.aoc.monitoring.asteroid

class AsteroidMap(mapData: List<String>) {
    private var map: Set<MapSector>

    init {
        map = mapData.mapIndexed { y, row ->
            row.chunked(1).mapIndexed { x, contents -> MapSector(contents, x, y) }
        }.flatten().toSet()
    }

    fun getRow(index: Int) = map.filter { it.y == index }.joinToString(separator = "")

    fun getAsteroidSightLine(x1: Int, y1: Int, x2: Int, y2: Int) {
        //https://stackoverflow.com/questions/14066933/direct-way-of-computing-clockwise-angle-between-2-vectors/16544330#16544330
        val asteroids = map.filter { it.hasAsteroid() }
        asteroids.map { sourceSector ->
            asteroids.map {
            }
        }
        val sectorOne = getSector(x1, y1)
        val sectorTwo = getSector(x2, y2)
    }

    private fun getSector(x: Int, y: Int): MapSector = map.find { it.x == x && it.y == y }!!
}