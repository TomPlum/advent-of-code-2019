package com.aoc.intcode.network

import com.aoc.intcode.computer.IntCodeComputer

class IntCodeComputerMesh(private val software: String) {

    private val computers = mutableMapOf<NetworkAddress, IntCodeComputer>()

    init {
        repeat(50) { computers[NetworkAddress(it.toLong())] = IntCodeComputer(software) }

        computers.forEach { (address, computer) ->
            computer.onNextBoot(address)
        }

    }

}