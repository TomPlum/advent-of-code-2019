package com.aoc.vault.new

class UnDirectedGraph<T>(private val vertices: List<T> = emptyList()) {
    var edges: Int = 0
    var adjacent: Map<T, MutableSet<NodeData<T>>> = vertices.associateWith { mutableSetOf() }

    fun addEdge(first: T, second: T, weight: Long, doors: Set<T>, keys: Set<T>) {
        if (!hasAdjacent(first, second)) {
            edges++
            adjacent[first]?.add(NodeData(second, weight, doors, keys))
        }
    }

    fun getAdjacentVertices(vertex: T): MutableSet<NodeData<T>>? = adjacent[vertex]

    fun hasAdjacent(first: T, second: T): Boolean = adjacent[first]?.map { it.key }?.contains(second) ?: false

    override fun toString(): String = "Vertices: ${vertices.size}, Edges: $edges\n" + adjacent.map { entry ->
        "${entry.key}: " + entry.value.joinToString(", ") { "${it.key}[${it.weight}](${it.doors})" }
    }.joinToString("\n")
}