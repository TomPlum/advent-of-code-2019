package com.aoc.fuel.factory.components

import java.util.*

class Chemical(name: String, quantity: Double) : ReactionComponent(name, quantity) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Chemical) return false
        return quantity == other.quantity && name == other.name
    }

    override fun hashCode() = Objects.hash(name)
}