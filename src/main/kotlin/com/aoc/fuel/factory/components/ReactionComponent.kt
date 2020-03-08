package com.aoc.fuel.factory.components

abstract class ReactionComponent(val name: String, val quantity: Double) {
    override fun toString() = "${quantity.toInt()} ${name.toUpperCase()}"
}