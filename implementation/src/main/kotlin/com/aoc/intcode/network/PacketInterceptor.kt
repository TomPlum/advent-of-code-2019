package com.aoc.intcode.network

import com.aoc.intcode.network.packet.Packet

interface PacketInterceptor {
    fun listen(packet: Packet): Packet?
}