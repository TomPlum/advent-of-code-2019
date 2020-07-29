package com.aoc.intcode.droid.cryo

import com.aoc.intcode.droid.Room

class DroidOutput(private val value: String) {
    fun parseRoom(): Room = Room(value.substringAfter("==").substringBefore("==").trim())
}