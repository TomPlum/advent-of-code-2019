package com.aoc.intcode.droid.cryo.exceptions

import java.lang.IllegalArgumentException
import com.aoc.intcode.droid.cryo.command.CommandParser

/**
 * Thrown by the [CommandParser] when an un-recognised command is parsed.
 */
class InvalidCommand(input: String) : IllegalArgumentException("Invalid command: $input")