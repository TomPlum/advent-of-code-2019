package com.aoc.fuel.factory

import com.aoc.fuel.factory.components.ReactionComponent

data class Reaction(val consumes: Set<ReactionComponent>, val produces: ReactionComponent) {
    override fun toString() = "${consumes.joinToString(separator = ", ")} => $produces"
}