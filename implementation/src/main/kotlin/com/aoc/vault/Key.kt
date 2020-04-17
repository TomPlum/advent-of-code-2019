package com.aoc.vault

import math.Point2D

data class Key(val name: Char, val pos: Point2D, val collectedKeys: Set<Key>) {
    private val keys = mutableMapOf<Key, Float>()

    fun mapTo(key: Key, weight: Float) = keys.put(key, weight)
}