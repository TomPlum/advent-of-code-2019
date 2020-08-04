package com.aoc.intcode.droid.cryo.command.types

import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.command.CommandParser

/**
 * A [Command] issued to the [CommandRuntime] to perform system functionality.
 * These commands are NOT issued to the [CryostasisDroid].
 * @see CommandParser
 */
abstract class SystemCommand : Command("sys")