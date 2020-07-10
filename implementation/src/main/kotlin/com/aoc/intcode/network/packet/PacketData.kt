package com.aoc.intcode.network.packet

data class PacketData(val x: Long, val y: Long) {
    override fun toString(): String = "($x, $y)"
}