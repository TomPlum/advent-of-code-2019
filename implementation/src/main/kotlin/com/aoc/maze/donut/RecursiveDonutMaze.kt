package com.aoc.maze.donut

import com.aoc.maze.donut.portal.Portal
import java.util.*

class RecursiveDonutMaze(data: List<String>) : PlutonianMaze(data) {
    private val usedPortals = Stack<Portal>()

    override fun findShortestPath(): Int {
        //Perform a DFS and map portal steps as each portal can only access a few others on each floor
        return 0
    }
}