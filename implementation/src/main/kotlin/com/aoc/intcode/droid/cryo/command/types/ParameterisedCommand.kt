package com.aoc.intcode.droid.cryo.command.types

/**
 * A version of a [Command] that accepts parameters to alter its behaviour.
 */
abstract class ParameterisedCommand(instruction: String, parameter: String) : Command("$instruction $parameter")