package com.aoc.vault.new

class DijkstraGraph<T> {
    private val nodes: MutableSet<DijkstraNode<T>> = mutableSetOf()

    fun addNode(node: DijkstraNode<T>) = nodes.add(node)

    fun getNode(name: T) = nodes.find { it.name == name } ?: throw IllegalArgumentException("Cannot find node $name")

    override fun toString(): String = nodes.joinToString("\n") { node ->
        "$node -> " + node.adjacentNodes().entries.joinToString { (adj, data) ->
            "${adj.name}[${data.weight}](${data.doors}){${data.keys}}"
        }
    }
}