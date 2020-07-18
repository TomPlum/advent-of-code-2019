package com.aoc.intcode.network

import com.aoc.intcode.computer.boot.BootMode
import com.aoc.intcode.computer.IntCodeComputer

/**
 * Uniquely identifies a [NetworkComputer] on an [IntCodeComputerNetwork]
 *
 * Also acts as a [BootMode] for an [IntCodeComputer].
 * @see IntCodeComputer.onNextBoot
 */
data class NetworkAddress(val value: Long) : BootMode {
    fun format32bit(): String = "192.168.1.$value"

    override fun getCode() = value

    override fun toString(): String = value.toString()
}