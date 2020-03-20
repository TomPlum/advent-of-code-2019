package com.aoc.radio

class Receiver {
    fun listen(data: String) = Signal(data.map { it.toString().toInt() })
}