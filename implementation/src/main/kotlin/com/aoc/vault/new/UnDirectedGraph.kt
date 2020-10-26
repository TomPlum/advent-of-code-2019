package com.aoc.vault.new

class UnDirectedGraph<T>(vertices: List<T> = emptyList()) {
    var edges: Int = 0
    var adjacent: Map<T, MutableSet<Pair<T, Long>>> = vertices.associateWith { mutableSetOf() }

    fun addEdge(first: T, second: T, weight: Long) {
        edges++
        adjacent[first]?.add(Pair(second, weight))
        adjacent[second]?.add(Pair(first, weight))
    }

    fun getAdjacentVertices(vertex: T): MutableSet<Pair<T, Long>>? = adjacent[vertex]

    override fun toString(): String = adjacent.map { entry ->
        "${entry.key}: " + entry.value.joinToString(", ") { "${it.first}[${it.second}]" }
    }.joinToString("\n")


}