package com.aoc.map

import java.util.*

class TestMapTile(private val data: Int) : MapTile<Int>(data) {
    fun isMyTestValue() = data == 12

    override fun equals(other: Any?): Boolean {
        if (other !is TestMapTile) return false
        return data == other.data
    }

    override fun hashCode(): Int {
        return Objects.hashCode(data)
    }
}