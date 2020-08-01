package com.aoc.intcode.droid.cryo.controller

import com.aoc.intcode.droid.cryo.security.AirlockPassword
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.intcode.droid.cryo.droid.Item
import com.aoc.intcode.droid.cryo.command.DropCommand
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand
import com.aoc.log.AdventLogger

class AutomaticDroidController(instructions: String) : CryostasisDroidController {
    private val droid = CryostasisDroid(instructions)

    override fun findPassword(): AirlockPassword {
        TODO("Implement algorithms to find all the below items automatically. I think blacklist must be hard-coded?")
        val whitelist = listOf("festive hat", "pointer", "prime number", "coin", "space heater", "astrolabe", "wreath", "dehydrated water")

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
                return droid.password
            }
        }
    }

    private fun <T> Collection<T>.powerset(): Set<Set<T>> = when {
        isEmpty() -> setOf(setOf())
        else -> drop(1).powerset().let { it + it.map { it + first() } }
    }
}