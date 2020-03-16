package com.aoc.fuel.factory.components

abstract class ReactionComponent(val name: String, val quantity: Int) {
    override fun toString() = "$quantity ${name.toUpperCase()}"
}