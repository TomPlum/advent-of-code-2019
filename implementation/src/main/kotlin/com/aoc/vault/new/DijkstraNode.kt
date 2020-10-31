package com.aoc.vault.new

import java.util.*

data class DijkstraNode<T>(val name: T) {
    private val adjacentNodes: MutableMap<DijkstraNode<T>, DijkstraNodeData> = mutableMapOf()
    var distance: Int = 2000000000
    var shortestPath: LinkedList<DijkstraNode<T>> = LinkedList()

    fun addDestination(node: DijkstraNode<T>, distance: Int, doors: Set<Char>, passedKeys: Set<Char>) {
        adjacentNodes[node] = DijkstraNodeData(distance, passedKeys, doors)
    }

    fun adjacentNodes(): Map<DijkstraNode<T>, DijkstraNodeData> = adjacentNodes.filter { it.key.name != this.name }

    fun accessibleAdjacentNodes(settledNodes: Set<DijkstraNode<Char>>): Map<DijkstraNode<T>, DijkstraNodeData> {
        val heldKeys = shortestPath.map { it.name as Char }.filter { it != '@' } //Keys picked up thus far
        return adjacentNodes()
                .filterNot { adj -> settledNodes.map { it.name }.contains(adj.key.name as Char) } //Exclude Itself
                .filter { adj -> heldKeys.containsAll(adj.value.getRequiredKeys()) } //Got keys for all doors?
                .filter { adj -> heldKeys.containsAll(adj.value.keys) } //Got all keys that we pass on the way?
    }

    fun updateDistance(evaluationNode: DijkstraNode<T>, edgeWeight: Int) {
        if (distance + edgeWeight < evaluationNode.distance) {
            evaluationNode.distance = distance + edgeWeight
            val shortestPath = LinkedList(this.shortestPath)
            shortestPath.add(this)
            evaluationNode.shortestPath = shortestPath
        }
    }

    fun getPath() = DijkstraPath(shortestPath.toCollection(mutableSetOf()))

    override fun toString(): String = name.toString()
}

data class DijkstraNodeData(val weight: Int, val keys: Set<Char>, val doors: Set<Char>) {
    fun getRequiredKeys(): Set<Char> = doors.map { it.toLowerCase() }.toSet()
}
