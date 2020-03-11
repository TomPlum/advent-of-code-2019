package com.aoc.fuel.factory

import kotlin.math.ceil
import kotlin.math.max

class Laboratory(private val reactions: List<Reaction>) {
    private val surplusComponents = mutableMapOf<String, Int>()

    fun minimumOreToProduceOneFuel(): Int {
        reactions.forEach { sourceReaction ->
            reactions.filter { it != sourceReaction }.forEach { targetReaction ->
                if (sourceReaction.consumes.map { it.name }.contains(targetReaction.produces.name)) {
                    sourceReaction.addPrerequisiteReaction(targetReaction)
                }
            }
        }

        return oreRequirementsFor("FUEL", 1.0)
    }

    private fun oreRequirementsFor(componentName: String, quantity: Double): Int {
        val producingReaction = findReactionWithOutput(componentName)
        val surplusQuantity = surplusComponents.getOrDefault(componentName, 0)
        val quantityNeeded = ceil(max(quantity - surplusQuantity, 0.0) / producingReaction.produces.quantity)
        val surplus = (producingReaction.produces.quantity * quantityNeeded) - (quantity - surplusQuantity)
        if (componentName != "ORE") surplusComponents[componentName] = surplus.toInt()
        var oreRequired = 0.0
        producingReaction.consumes.forEach {
            if (it.name == "ORE") {
                oreRequired += quantityNeeded * it.quantity
            } else {
                oreRequired += oreRequirementsFor(it.name, quantityNeeded * it.quantity)
            }
        }
        return oreRequired.toInt()
    }

    private fun findReactionWithOutput(componentName: String) = reactions.find { it.produces.name == componentName }!!
}