package com.aoc.intcode.network

import java.util.*

class NetworkInterfaceController(private val software: String) {
    private val packetQueue = PriorityQueue<Packet>()

    fun transmit(address: NetworkAddress): Packet = Packet(0,0)

    fun receive() = 0
}