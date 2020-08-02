package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.types.ItemCommand

data class DropCommand(private val itemName: String) : ItemCommand("drop", itemName)