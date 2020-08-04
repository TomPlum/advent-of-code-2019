package com.aoc.intcode.droid.cryo.command.system

import com.aoc.intcode.droid.cryo.command.types.SystemCommand
import com.aoc.intcode.droid.cryo.command.runtime.CommandRuntime
import com.aoc.intcode.droid.cryo.command.CommandParser

/**
 * A [SystemCommand] that terminates the [CommandRuntime].
 * @see CommandParser
 */
class QuitCommand : SystemCommand()