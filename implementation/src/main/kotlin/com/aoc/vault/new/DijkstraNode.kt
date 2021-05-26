package com.aoc.vault.new

import java.util.*

/**
 * A single node in a [DijkstraGraph] that maintains an adjacency list of other nodes.
 */
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
                .filter { adj -> heldKeys.containsAll(adj.value.keys) } //Got all keys that we pass on the way?
                .filterNot { adj -> shortestPath.contains(adj.key) }
    }

    fun adjacentNodes(currentState: NodeState<T>): Map<DijkstraNode<T>, DijkstraNodeData<T>> {
        val heldKeys = currentState.keysCollected
        return adjacentNodes()
                .filter { adj -> heldKeys.containsAll(adj.value.getRequiredKeys()) } //Got keys for all doors?
                .filter { adj -> heldKeys.containsAll(adj.value.keys) } //Got all keys that we pass on the way?
                .filterNot { adj -> heldKeys.contains(adj.key.name) } //Don't include any we've already picked up.
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

    fun getPath() = DijkstraPath(shortestPath.toCollection(mutableSetOf()) + this)

    /**
     * The state of the current [DijkstraNode].
     * Unlike a normal implementation of Dijkstra's Algorithm, we have to consider the keys that have been collected
     * up until the point of reaching the current node. This means that the current state of node at any point in time
     * is defined by the [name] and all the keys collected from the [shortestPath] (including itself).
     */
    fun getCurrentState(): NodeState<T> = NodeState(name, shortestPath.map { it.name } + name)

    fun getTargetState(evaluationNode: NodeState<T>): NodeState<T> = NodeState(name, shortestPath.map { it.name } + name + evaluationNode.name)

    override fun toString(): String = name.toString()

    override fun equals(other: Any?): Boolean {
        if (other !is DijkstraNode<*>) return false
        return this.name == other.name && this.shortestPath.map { it.name } + this.name == other.shortestPath.map { it.name } + other.name
    }

    override fun hashCode(): Int {
        return this.name.hashCode() + this.shortestPath.hashCode()
    }
}
