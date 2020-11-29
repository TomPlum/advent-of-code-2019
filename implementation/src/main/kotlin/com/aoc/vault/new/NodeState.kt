package com.aoc.vault.new

data class NodeState<T>(val name: T, val keysCollected: List<T>) {

    fun getCurrentKeyName(): T = keysCollected.last()

    fun plus(other: NodeState<T>): NodeState<T> = NodeState(other.name, other.keysCollected + keysCollected)

    fun plus(key: T) = NodeState(key, keysCollected + key)

    fun hasCollectedAllKeys(total: Int): Boolean = keysCollected.size == total
}