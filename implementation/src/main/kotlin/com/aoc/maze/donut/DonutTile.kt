package com.aoc.maze.donut

import com.aoc.map.MapTile

open class DonutTile(value: Char) : MapTile<Char>(value) {
    fun isPortalEntrance() = value == '@'

    fun isPortalMarker() = value.isLetter() && value.isUpperCase()

    fun isTraversable() = value == '.'

    fun isExit() = value == 'x'
}