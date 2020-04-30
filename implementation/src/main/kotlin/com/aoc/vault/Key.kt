package com.aoc.vault

import math.Point2D

data class Key(val name: Char, val pos: Point2D, val collectedKeys: Set<Key>) {
    val linkedKeys = mutableMapOf<Key, Float>()

    fun mapTo(key: Key, weight: Float) = linkedKeys.put(key, weight)

    fun getAllChildren(): Set<Key> = linkedKeys.keys + linkedKeys.keys.flatMap { it.getAllChildren() }

    fun getLinkedKeyWeight(name: Char) = linkedKeys.filter { it.key.name == name }.values.first()

    fun steps() = collectedKeys.sumBy { it.linkedKeys.values.sum().toInt() }

    fun collectedKeysQuantity(): Int = collectedKeys.size + 1

    fun pathString(): String = "[${(collectedKeys + this).joinToString(", ") { it.name.toString() } }]"

    override fun toString() = name.toString()
}