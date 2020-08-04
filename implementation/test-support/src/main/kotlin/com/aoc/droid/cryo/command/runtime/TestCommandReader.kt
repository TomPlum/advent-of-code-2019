package com.aoc.droid.cryo.command.runtime

import com.aoc.intcode.droid.cryo.command.runtime.CommandReader

class TestCommandReader : CommandReader {

    private val commands = mutableListOf<String>()

    @ExperimentalStdlibApi
    override fun read(): String? = commands.removeFirstOrNull()

    fun inputCommand(value: String) = commands.add(value)
}