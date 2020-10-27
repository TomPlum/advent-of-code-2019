package com.aoc.vault.new

class UnDirectedGraph<T>(private val vertices: List<T> = emptyList()) {
    var edges: Int = 0
    var adjacent: Map<T, MutableSet<Triple<T, Long, Set<T>>>> = vertices.associateWith { mutableSetOf() }

    fun addEdge(first: T, second: T, weight: Long, doors: Set<T>) {
        edges++
        adjacent[first]?.add(Triple(second, weight, doors))
        adjacent[second]?.add(Triple(first, weight, doors))
    }

    fun getAdjacentVertices(vertex: T): MutableSet<Triple<T, Long, Set<T>>>? = adjacent[vertex]

    fun hasAdjacent(first: T, second: T): Boolean = adjacent[first]?.map { it.first }?.contains(second) ?: false

    override fun toString(): String = "Vertices: ${vertices.size}, Edges: $edges\n" + adjacent.map { entry ->
        "${entry.key}: " + entry.value.joinToString(", ") { "${it.first}[${it.second}](${it.third})" }
    }.joinToString("\n")


}