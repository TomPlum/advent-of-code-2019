package com.aoc.intcode.droid.cryo.command.runtime

import com.aoc.intcode.droid.cryo.droid.CryostasisDroid

/**
 * The default [CommandReader] implementation for the [CryostasisDroid].
 */
class DroidCommandReader : CommandReader {
    override fun read(): String? = readLine()
}