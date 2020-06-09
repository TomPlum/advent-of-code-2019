package com.aoc.maze.donut.portal

import com.aoc.math.Point3D

data class WarpCode(val charOne: Char, val posOne: Point3D, val charTwo: Char, val posTwo: Point3D) {

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