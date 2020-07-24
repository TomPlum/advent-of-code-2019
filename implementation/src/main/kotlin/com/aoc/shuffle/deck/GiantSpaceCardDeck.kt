package com.aoc.shuffle.deck

import com.aoc.math.LinearFunction
import java.math.BigInteger

class GiantSpaceCardDeck(private val function: LinearFunction, private val deckSize: BigInteger) {
    fun getCard(position: BigInteger): BigInteger = function.apply(position).mod(deckSize)
}