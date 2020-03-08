package com.aoc.fuel.factory.components

import java.util.*

class Ore(quantity: Double) : ReactionComponent("ORE", quantity) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Ore) return false
        return quantity == other.quantity
    }

    override fun hashCode() = Objects.hash(quantity)
}