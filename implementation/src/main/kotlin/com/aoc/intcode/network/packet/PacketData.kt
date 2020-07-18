package com.aoc.intcode.network.packet

import com.aoc.intcode.network.IntCodeComputerNetwork

/**
 * Stores data for transmission over an [IntCodeComputerNetwork].
 * @see PacketData
 */
data class PacketData(val x: Long, val y: Long) {
    override fun toString(): String = "($x, $y)"
}