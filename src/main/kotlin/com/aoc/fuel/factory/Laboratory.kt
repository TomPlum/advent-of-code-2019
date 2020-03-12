package com.aoc.fuel.factory

import kotlin.math.ceil
import kotlin.math.max
import com.aoc.fuel.factory.components.Ore
import com.aoc.fuel.factory.components.ReactionComponent

class Laboratory(private val reactions: List<Reaction>) {
    private val surplusComponents = mutableMapOf<String, Int>()

    fun minimumOreToProduceOneFuel() = oreRequirementsFor("FUEL", 1)

    /**
     * Calculates the minimum amount of [Ore] required to produce [quantity] amount of the given
     * [ReactionComponent] specified by the given [componentName].
     * @param componentName The [ReactionComponent.name]
     * @param quantity The current [ReactionComponent.quantity]. This is multiplied recursively to calculate [Ore]
     */
    private fun oreRequirementsFor(componentName: String, quantity: Int): Int {
        val producingReaction = findReactionThatProduces(componentName)
        val surplusQuantity = surplusComponents.getOrDefault(componentName, 0)
        val quantityNeeded = ceil((max(quantity - surplusQuantity, 0).toDouble() / producingReaction.produces.quantity.toDouble())).toInt()
        val surplus = (producingReaction.produces.quantity * quantityNeeded) - (quantity - surplusQuantity)
        if (componentName != "ORE") surplusComponents[componentName] = surplus
        var oreRequired = 0
        producingReaction.consumes.forEach {
            oreRequired += if (it.name == "ORE") {
                quantityNeeded * it.quantity
            } else {
                oreRequirementsFor(it.name, quantityNeeded * it.quantity)
            }
        }
        return oreRequired
    }

    private fun findReactionThatProduces(componentName: String) = reactions.find { it.produces.name == componentName }!!
}