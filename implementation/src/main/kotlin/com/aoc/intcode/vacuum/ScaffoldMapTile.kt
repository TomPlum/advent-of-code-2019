package com.aoc.intcode.vacuum

data class ScaffoldMapTile(private var value: Char) {

    fun isScaffold() = value == '#'

    fun isIntersection() = value == 'O'

    companion object {
        fun empty() = ScaffoldMapTile('.')
        fun intersection() = ScaffoldMapTile('O')
        fun unknown() = ScaffoldMapTile('!')
    }

    override fun toString() = value.toString()
}