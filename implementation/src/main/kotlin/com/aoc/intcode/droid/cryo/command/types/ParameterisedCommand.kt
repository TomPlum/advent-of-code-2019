package com.aoc.intcode.droid.cryo.command.types

abstract class ParameterisedCommand(instruction: String, parameter: String) : Command("$instruction $parameter")