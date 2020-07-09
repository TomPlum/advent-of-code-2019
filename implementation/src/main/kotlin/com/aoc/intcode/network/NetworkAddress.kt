package com.aoc.intcode.network

import com.aoc.intcode.computer.boot.BootMode

data class NetworkAddress(val value: Long) : BootMode {
    override fun getCode() = value
}