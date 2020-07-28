package com.aoc.intcode.droid.cryo.command

import java.lang.IllegalArgumentException

class InvalidCommand(input: String) : IllegalArgumentException("Invalid command: $input")