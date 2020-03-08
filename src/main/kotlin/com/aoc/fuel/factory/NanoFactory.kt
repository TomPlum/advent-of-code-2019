package com.aoc.fuel.factory

import com.aoc.fuel.factory.components.Chemical
import com.aoc.fuel.factory.components.Fuel
import com.aoc.fuel.factory.components.Ore

class NanoFactory(private val reactionData: List<String>) {
    fun produceReactionList(): List<Reaction> = reactionData.map {
        val inputStrings = it.split("=>")[0].trim().split(", ")
        val inputs = inputStrings
                .map { string -> string.split(" ") }
                .map { term ->  parseComponent(term[0].toDouble(), term[1]) }
                .toSet()

        val outputString = it.split("=>")[1].trim().split(" ")
        val output = parseComponent(outputString[0].toDouble(), outputString[1])

        Reaction(inputs, output)
    }

    private fun parseComponent(quantity: Double, name: String) = when(name) {
        "ORE" -> Ore(quantity)
        "FUEL" -> Fuel(quantity)
        else -> Chemical(name, quantity)
    }
}