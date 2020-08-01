package com.aoc.solutions

import com.aoc.Day
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.CryostasisDroid
import com.aoc.intcode.droid.cryo.Item
import com.aoc.intcode.droid.cryo.command.DropCommand
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand
import com.aoc.log.AdventLogger

fun main() {
    /*val input = InputReader.read<String>(Day(25)).asSingleString()
    val runtime = CommandRuntime(input)
    runtime.start()*/
    autoSolve()
}

private fun autoSolve() {
    val input = InputReader.read<String>(Day(25)).asSingleString()
    val droid = CryostasisDroid(input)
    val whitelist = listOf("festive hat", "pointer", "prime number", "coin", "space heater", "astrolabe", "wreath", "dehydrated water")
    droid.boot()

    //Pick up all the items
    droid.command(MovementCommand("south"))
    droid.command(TakeCommand("festive hat"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("west"))
    droid.command(MovementCommand("south"))
    droid.command(TakeCommand("pointer"))
    droid.command(MovementCommand("south"))
    droid.command(TakeCommand("prime number"))
    droid.command(MovementCommand("west"))
    droid.command(TakeCommand("coin"))
    droid.command(MovementCommand("east"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("east"))
    droid.command(MovementCommand("east"))
    droid.command(MovementCommand("south"))
    droid.command(MovementCommand("south"))
    droid.command(TakeCommand("space heater"))
    droid.command(MovementCommand("south"))
    droid.command(TakeCommand("astrolabe"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("north"))
    droid.command(TakeCommand("wreath"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("west"))
    droid.command(TakeCommand("dehydrated water"))
    droid.command(MovementCommand("north"))
    droid.command(MovementCommand("east"))

    whitelist.powerset().map { list -> list.map { name -> Item(name) }.toSet() }.toSet().forEach { items ->
        AdventLogger.info("Trying items combination: $items...")
        while (droid.inventory.getAllItems().toSet() != items) {
            items.filterNot { droid.inventory.getAllItems().contains(it) }.forEach {
                droid.command(TakeCommand(it.name))
            }

            droid.inventory.getAllItems().filterNot { items.contains(it) }.forEach {
                droid.command(DropCommand(it.name))
            }
        }
        droid.command(MovementCommand("south"))

        if (droid.password.isValid()) {
            println("Solution (Part 1): ${droid.password}")
            return
        }
    }

}

private fun <T> Collection<T>.powerset(): Set<Set<T>> = when {
    isEmpty() -> setOf(setOf())
    else -> drop(1).powerset().let { it + it.map { it + first() } }
}