package com.aoc.intcode.hull

enum class HullPaint(val colourCode: Int) {
    BLACK(0), WHITE(1);

    override fun toString() = if(colourCode == 0)  " " else "#"

}