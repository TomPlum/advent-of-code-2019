package com.aoc.vault.new

class UnDirectedGraph<T>(private val vertices: List<T> = emptyList()) {
    var edges: Int = 0
    var adjacent: Map<T, MutableSet<GraphNode<T>>> = vertices.associateWith { mutableSetOf() }

    fun addEdge(first: T, second: T, weight: Long, doors: Set<T>, keys: Set<T>) {
        if (!hasAdjacent(first, second)) {
            edges++
            adjacent[first]?.add(GraphNode(second, weight, doors, keys))
            //adjacent[second]?.add(GraphNode(first, weight, doors, keys))
        }
    }

    fun getAdjacentVertices(vertex: T): MutableSet<GraphNode<T>>? = adjacent[vertex]

    fun hasAdjacent(first: T, second: T): Boolean = adjacent[first]?.map { it.key }?.contains(second) ?: false

    override fun toString(): String = "Vertices: ${vertices.size}, Edges: $edges\n" + adjacent.map { entry ->
        "${entry.key}: " + entry.value.joinToString(", ") { "${it.key}[${it.weight}](${it.doors})" }
    }.joinToString("\n")
}

data class GraphNode<T>(val key: T, val weight: Long, val doors: Set<T>, val keys: Set<T>)