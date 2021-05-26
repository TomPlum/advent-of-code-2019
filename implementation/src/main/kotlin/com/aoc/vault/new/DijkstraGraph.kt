package com.aoc.vault.new

/**
 * Maintains a [Set] of [DijkstraNode]s.
 */
class DijkstraGraph<T> {
    private val nodes: MutableSet<DijkstraNode<T>> = mutableSetOf()

    fun addNode(node: DijkstraNode<T>) = nodes.add(node)

    fun getNode(name: T) = nodes.find { it.name == name } ?: throw IllegalArgumentException("Cannot find node $name")

    fun calculatePathDistance(keys: List<T>): Int = keys.zipWithNext { current, next ->
        getNode(current).adjacentNodes().filter { it.key.name == next }.values.first().weight
    }.sum()

    override fun toString(): String = nodes.joinToString("\n") { node ->
        "$node -> " + node.adjacentNodes().entries.joinToString { (adj, data) ->
            "${adj.name}[${data.weight}](${data.doors}){${data.keys}}"
        }
    }
}