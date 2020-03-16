package com.aoc.fuel.factory

import kotlin.math.ceil
import kotlin.math.max
import com.aoc.fuel.factory.components.*

class Laboratory(private val reactions: List<Reaction>) {
    private val surplusComponents = mutableMapOf<String, Int>()

    /**
     * Calculates the quantity of [Ore] required to produce 1 unit of [Fuel].
     * Wasted [ReactionComponent] are kept to a minimum by utilising surplus [Chemical]
     * from previous [Reaction].
     */
    fun minimumOreToProduceOneFuel() = oreRequirementsFor("FUEL", 1)

    /**
     * Calculates the maximum amount of [Fuel] that can be produced from 1x10^12 [Ore]
     */
    fun maximumFuelProducedFromOneTrillionOre(): Long {
        var oreRemaining = 1_000_000_000_000
        var fuel = 0L
        var fuelToProduce = 950 //Arbitrary starting value. 950 produces a relatively quick runtime.
        var approachingLimit = false

        while (oreRemaining >= 0) {
            val oreRequired = oreRequirementsFor("FUEL", fuelToProduce)
            if (!approachingLimit && oreRequired > oreRemaining) {
                approachingLimit = true
                fuelToProduce = 1
                continue
            }

            if (oreRequired > oreRemaining) return fuel
            oreRemaining -= oreRequired
            fuel += fuelToProduce
        }

        return fuel
    }

    /**
     * Calculates the minimum amount of [Ore] required to produce [quantity] amount of the given
     * [ReactionComponent] specified by the given [componentName].
     * @param componentName The [ReactionComponent.name]
     * @param quantity The current [ReactionComponent.quantity]. This is multiplied recursively to calculate [Ore]
     */
    private fun oreRequirementsFor(componentName: String, quantity: Int): Int {
        val producingReaction = reactions.find { it.produces.name == componentName }!!

        val surplusQuantity = surplusComponents.getOrDefault(componentName, 0)
        val timesToReact = ceil((max(quantity - surplusQuantity, 0).toDouble() / producingReaction.produces.quantity.toDouble())).toInt()

        val surplus = (producingReaction.produces.quantity * timesToReact) - (quantity - surplusQuantity)
        surplusComponents[componentName] = surplus

        var oreRequired = 0
        producingReaction.consumes.forEach {
            oreRequired += if (it.name == "ORE") {
                timesToReact * it.quantity
            } else {
                oreRequirementsFor(it.name, timesToReact * it.quantity)
            }
        }
        return oreRequired
    }

}