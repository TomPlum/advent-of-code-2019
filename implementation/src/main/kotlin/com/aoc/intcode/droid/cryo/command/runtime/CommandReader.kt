package com.aoc.intcode.droid.cryo.command.runtime

/**
 * A reader that accepts text input from the user.
 */
interface CommandReader {
    fun read(): String?
}