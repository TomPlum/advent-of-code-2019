package com.aoc.fuel.factory

import kotlin.math.ceil

class Laboratory(private val reactions: List<Reaction>) {
    private var oreRequired = 0
    private var currentReactionOre = 0

    fun minimumOreToProduceOneFuel(): Int {
        val fuelRequirements = findReactionWithOutput("FUEL")
        return doSomethingRecursively(fuelRequirements, 1)
    }

    fun doSomethingRecursively(reaction: Reaction, quantity: Int): Int {
        reaction.input.forEach {
            if (it.name == "ORE") return quantity * it.quantity.toInt()
            val producingReaction = findReactionWithOutput(it.name)
            val quantityNeeded = ceil(it.quantity / producingReaction.output.quantity).toInt()
            oreRequired += doSomethingRecursively(producingReaction, quantityNeeded)
        }
        return oreRequired
    }

    private fun findReactionWithOutput(componentName: String) = reactions.find { it.output.name == componentName }!!
}