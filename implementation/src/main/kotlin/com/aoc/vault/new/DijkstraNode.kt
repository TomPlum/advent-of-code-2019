package com.aoc.vault.new

import java.util.*

data class DijkstraNode<T>(val name: T) {
    private val adjacentNodes: MutableMap<DijkstraNode<T>, DijkstraNodeData<T>> = mutableMapOf()
    private val heldKeys: MutableSet<T> = mutableSetOf(this.name)
    var distance: Int = 2000000000
    var shortestPath: LinkedList<DijkstraNode<T>> = LinkedList()

    fun addDestination(node: DijkstraNode<T>, distance: Int, doors: Set<T>, passedKeys: Set<T>) {
        adjacentNodes[node] = DijkstraNodeData(distance, passedKeys, doors)
    }

    fun adjacentNodes(): Map<DijkstraNode<T>, DijkstraNodeData<T>> = adjacentNodes.filter { it.key.name != this.name }

    fun accessibleAdjacentNodes(): Map<DijkstraNode<T>, DijkstraNodeData<T>> {
        val heldKeys = shortestPath.filter { it.name != '@' }.map { it.name } + this.name
        return adjacentNodes()
                .filter { adj -> heldKeys.containsAll(adj.value.getRequiredKeys()) } //Got keys for all doors?
                //.filter { adj -> heldKeys.containsAll(adj.value.keys) } //Got all keys that we pass on the way?
    }

    fun updateDistance(evaluationNode: DijkstraNode<T>, edgeWeight: Int) {
        if (distance + edgeWeight < evaluationNode.distance) {
            evaluationNode.distance = distance + edgeWeight
            val shortestPath = LinkedList(this.shortestPath)
            shortestPath.add(this)
            evaluationNode.shortestPath = shortestPath
            evaluationNode.addKey(name)
        }
    }

    private fun addKey(key: T) = heldKeys.add(key)

    fun hasCollectedAllKeys(totalQuantity: Int) = heldKeys.size == totalQuantity

    fun getPath() = DijkstraPath(shortestPath.toCollection(mutableSetOf()))

    override fun toString(): String = name.toString()

    override fun equals(other: Any?): Boolean {
        if (other !is DijkstraNode<*>) return false
        return this.name == other.name && this.heldKeys == other.heldKeys;
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
