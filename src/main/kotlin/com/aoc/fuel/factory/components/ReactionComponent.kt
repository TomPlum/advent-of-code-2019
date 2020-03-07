package com.aoc.fuel.factory.components

abstract class ReactionComponent(private val name: String, private val quantity: Int) {
    override fun toString() = "$quantity ${name.toUpperCase()}"
}