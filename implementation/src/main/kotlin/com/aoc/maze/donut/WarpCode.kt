package com.aoc.maze.donut

import com.aoc.math.Point2D

data class WarpCode(val charOne: Char, val posOne: Point2D, val charTwo: Char, val posTwo: Point2D) {

    fun getPositions() = setOf(posOne, posTwo)

    fun isEntrance() = charOne == 'A' && charTwo == 'A'

    fun isExit() = charOne == 'Z' && charTwo == 'Z'

    fun isMatching(other: WarpCode): Boolean {
        val hasSameCode = (charOne == other.charOne || charOne == other.charTwo) &&
                (charTwo == other.charOne || charTwo == other.charTwo)
        val hasDifferentPosition = (posOne != other.posOne && posOne != other.posTwo) &&
                (posTwo != other.posOne && posTwo != other.posTwo)
        return hasSameCode && hasDifferentPosition
    }

    override fun toString() = charOne.toString() + charTwo.toString()

}