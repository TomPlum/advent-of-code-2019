package com.aoc.maze.donut

import map.MapTile

open class DonutTile(value: Char) : MapTile<Char>(value) {
    fun isPortalMarker() = value.isLetter() && value.isUpperCase()

    fun isTraversable() = value == '.'
}