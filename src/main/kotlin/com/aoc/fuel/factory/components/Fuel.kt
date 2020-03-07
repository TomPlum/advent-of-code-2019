package com.aoc.fuel.factory.components

import java.util.*

class Fuel(private val quantity: Int) : ReactionComponent("FUEL", quantity) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Fuel) return false
        return quantity == other.quantity
    }

    override fun hashCode() = Objects.hash(quantity)
}