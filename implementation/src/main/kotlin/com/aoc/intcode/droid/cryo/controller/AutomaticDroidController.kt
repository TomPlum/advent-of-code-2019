package com.aoc.intcode.droid.cryo.controller

import com.aoc.extensions.powerSet
import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.command.DropCommand
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand
import com.aoc.log.AdventLogger

class AutomaticDroidController(instructions: String) : CryostasisDroidController {
    private val droid = CryostasisDroid(instructions)

    init {
        droid.boot()
    }

    override fun findPassword(): AirlockPassword {
        //TODO: Implement algorithms to find all the below items automatically. I think blacklist must be hard-coded?
        val whitelist = listOf("festive hat", "pointer", "prime number", "coin", "space heater", "astrolabe", "wreath", "dehydrated water")

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

        whitelist.powerSet().map { list -> list.map { name -> Item(name) }.toSet() }.toSet().forEach { items ->
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
                return droid.password
            }
        }

        throw IllegalStateException("Failed to find a valid combination of items.")
    }
}