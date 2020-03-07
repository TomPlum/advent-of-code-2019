package com.aoc.fuel.factory

import com.aoc.fuel.factory.components.ReactionComponent

data class Reaction(val input: Set<ReactionComponent>, val output: ReactionComponent) {
    override fun toString() = "${input.joinToString(separator = ", ")} => $output"
}