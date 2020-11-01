package com.aoc.vault.new

data class DijkstraNodeData(val weight: Int, val keys: Set<Char>, val doors: Set<Char>) {
    fun getRequiredKeys(): Set<Char> = doors.map { it.toLowerCase() }.toSet()
}
