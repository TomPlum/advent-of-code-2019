package com.aoc.vault.new

/**
 * The data mapped against a single [DijkstraNode].
 * @param weight The number of steps required to reach the respective node.
 * @param keys The keys that are passed a long the way to reaching the node.
 * @param doors The doors that must be unlocked on the way to reaching the target node.
 */
data class DijkstraNodeData<T>(val weight: Int, val keys: Set<T>, val doors: Set<T>) {
    /**
     * @return The required set of keys to unlock all [doors] en-route to the target node.
     */
    fun getRequiredKeys(): Set<T> = doors.map { it as Char }.map { it.toLowerCase() }.map{ it as T}.toSet()
}
