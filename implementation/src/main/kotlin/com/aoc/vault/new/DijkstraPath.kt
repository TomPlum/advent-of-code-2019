package com.aoc.vault.new

class DijkstraPath<T>(private val nodes: Set<DijkstraNode<T>>) {
    fun getDistance() = nodes.sumBy { it.distance }

    override fun toString(): String  = nodes.joinToString(" -> ") { "${it.name}" }
}