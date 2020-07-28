package com.aoc.intcode.droid.cryo.command

abstract class ParameterisedCommand(instruction: String, parameter: String) : Command(instruction + parameter)