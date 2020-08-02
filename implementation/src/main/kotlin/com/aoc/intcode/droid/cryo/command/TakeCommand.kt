package com.aoc.intcode.droid.cryo.command

import com.aoc.intcode.droid.cryo.command.types.ItemCommand

data class TakeCommand(private val itemName: String) : ItemCommand("take", itemName)