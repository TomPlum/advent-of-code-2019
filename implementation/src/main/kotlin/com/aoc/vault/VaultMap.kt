package com.aoc.vault

import map.Map
import math.Point2D

class VaultMap(private val initialData: List<String>) : Map<VaultTile>() {

    init {
        var x = 0
        var y = 0
        initialData.forEach { row ->
            row.forEach { column ->
                addTile(Point2D(x, y), VaultTile(column))
               x++
            }
            y++
        }
    }

}