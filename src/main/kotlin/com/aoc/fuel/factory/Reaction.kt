package com.aoc.fuel.factory

import com.aoc.fuel.factory.components.Ore
import com.aoc.fuel.factory.components.ReactionComponent
import kotlin.math.ceil

data class Reaction(val consumes: Set<ReactionComponent>, val produces: ReactionComponent) {
    val prerequisiteReactions = mutableSetOf<Reaction>()

    fun addPrerequisiteReaction(reaction: Reaction) = prerequisiteReactions.add(reaction)

    fun getAmount(): Int {
        if (consumes.first() is Ore) {
            return this.consumes.first().quantity.toInt()// * quantityNeeded
        } else {
            val a = prerequisiteReactions.sumBy { preReq ->
                val component = consumes.find { preReq.produces.name == it.name }
                val reactionQuantity = ceil(component!!.quantity / preReq.produces.quantity).toInt()
                val amount = preReq.getAmount()
                amount * reactionQuantity
            }
            return a
           //return prerequisiteReactions.map { it.getAmount() * it.produces.quantity  }.sum()
        }
    }

    override fun toString() = "${consumes.joinToString(separator = ", ")} => $produces"
}