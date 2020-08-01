package com.aoc.intcode.droid.cryo.exceptions

import java.lang.IllegalArgumentException

class InvalidCommand(input: String) : IllegalArgumentException("Invalid command: $input")