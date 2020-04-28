package com.aoc.vault

import math.Point2D

data class Key(val name: Char, val pos: Point2D, val collectedKeys: Set<Key>) {
    val keys = mutableMapOf<Key, Float>()

    fun mapTo(key: Key, weight: Float) = keys.put(key, weight)

    fun getAllChildren(): Set<Key> = keys.keys + keys.keys.flatMap { it.getAllChildren() }

    fun getLinkedKey(name: Char) = keys.filter { it.key.name == name }
}