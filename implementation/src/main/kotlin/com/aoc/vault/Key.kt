package com.aoc.vault

import com.aoc.math.Point2D

data class Key(val name: Char, val pos: Point2D, val collectedKeys: List<Key>) {
    val linkedKeys = mutableMapOf<Key, Float>()

    fun linkTo(key: Key, weight: Float) = linkedKeys.put(key, weight)

    fun getAllChildren(): List<Key> = linkedKeys.keys.toList() + linkedKeys.keys.flatMap { it.getAllChildren() }.toList()

    fun getLinkedKeyWeight(name: Char) = linkedKeys.filter { it.key.name == name }.values.first()

    fun steps() = collectedKeys.sumBy { it.linkedKeys.values.sum().toInt() }

    fun collectedKeysQuantity(): Int = collectedKeys.size + 1

    fun pathString(): String = "[${(collectedKeys + this).joinToString(", ") { it.name.toString() } }]"

    fun hasTransitivelyLinkedKey(key: Key) = getAllChildren().contains(key)

    override fun equals(other: Any?): Boolean {
        if (other !is Key) return false
        if (name != other.name) return false
        if (pos != other.pos) return false
        if (collectedKeys.map { it.name } != other.collectedKeys.map { it.name }) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString() = name.toString()
}