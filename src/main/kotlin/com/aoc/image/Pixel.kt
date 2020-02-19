package com.aoc.image

enum class Pixel(private val colour: Int) {
    BLACK(0), WHITE(1), TRANSPARENT(2);

    companion object {
        fun fromColourCode(type: Int): Pixel = values().associateBy(Pixel::colour)[type] ?: error("Invalid Colour Code")
    }

    override fun toString(): String = colour.toString()

}