package com.aoc.vault

import map.MapTile

class VaultTile(private val marker: Char) : MapTile<Char>(marker) {
    fun isEntrance() = marker == '@'

    fun isKey() = marker.isLetter() && marker.isLowerCase()

    fun isWall() = marker == '#'

    fun isDoor() = marker.isLetter() && marker.isUpperCase()
}