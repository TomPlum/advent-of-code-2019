package com.aoc.intcode.droid.cryo.command.runtime

class DroidCommandReader : CommandReader {
    override fun read(): String? = readLine()
}