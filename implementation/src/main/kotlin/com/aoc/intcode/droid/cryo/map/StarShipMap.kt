package com.aoc.intcode.droid.cryo.map

import com.aoc.intcode.droid.cryo.map.StarShipMap.CornerGlyph.*
import com.aoc.intcode.droid.cryo.map.StarShipMap.EdgeGlyph.*
import com.aoc.intcode.droid.cryo.droid.CryostasisDroid
import com.aoc.map.AdventMap2D
import com.aoc.math.Direction
import com.aoc.math.Direction.*
import com.aoc.math.Point2D

/**
 * A map of Santa's Starship recorded and maintained by the [CryostasisDroid].
 */
class StarShipMap : AdventMap2D<Room>() {

    /**
     * The current position of the [CryostasisDroid].
     */
    var droidPosition = Point2D(0, 0)

    /**
     * Records the given [room] in the map at the given [position].
     */
    fun addRoom(position: Point2D, room: Room) = addTile(position, room)

    /**
     * Retrieves the [Room] at the given [position].
     */
    fun getRoom(position: Point2D) = getTile(position)

    /**
     * @return true if the [StarShipMap] contains a [Room] at the given [position].
     */
    fun hasRoomAt(position: Point2D) = hasRecorded(position)

    /**
     * @return The number of rooms current recorded in the map.
     */
    fun rooms() = tileQuantity()

    /**
     * Creates an ASCII string visualisation of the map.
     */
    fun display(): String {
        return (yMax() downTo yMin()).joinToString("\n") { y ->
            val roomString = StringBuilder()

            val xBreath = xMin()..xMax()

            xBreath.forEach { x -> roomString.append(getTopLayer(x, y)) }
            roomString.append("\n")

            xBreath.forEach { x -> roomString.append(getNameLayer(x, y)) }
            roomString.append("\n")

            xBreath.forEach { x -> roomString.append(getHorizontalCorridorLayer(x, y)) }
            roomString.append("\n")

            xBreath.forEach { x -> roomString.append(getFirstItemLayer(x, y)) }

            //Only append the bottom layer if we're drawing the bottom layer of rooms on the map
            if (y == yMin()) {
                roomString.append("\n")
                xBreath.forEach { x -> roomString.append(getBottomLayer(x, y)) }
            }

            roomString
        }
    }

    private fun Point2D.hasRoom(direction: Direction) = hasRecorded(this.shift(direction))

    private fun getTopLayer(x: Int, y: Int): String {
        val room = getTile(Point2D(x, y), Room.empty())
        return getTopLeftCorner(Point2D(x, y)).toString() + getTop(room) + getTopRightCorner(Point2D(x, y))
    }

    private fun getBottomLayer(x: Int, y: Int): String {
        val pos = Point2D(x, y)
        val room = getTile(pos, Room.empty())
        return getBottomLeftCorner(pos).toString() + getBottom(room) + getBottomRightCorner(pos)
    }

    private fun getNameLayer(x: Int, y: Int): String {
        val room = getTile(Point2D(x, y), Room.empty())

        val layer = StringBuilder(VERTICAL.toString())
        val nameLength = room.name.length
        if (nameLength <= 12) {
            val leftPadding = (12 - nameLength) / 2
            val rightPadding = 12 - leftPadding - nameLength
            layer.append(" ".repeat(leftPadding)).append(room.name).append(" ".repeat(rightPadding))
        } else {
            layer.append(room.name.substring(0, 9)).append("...")
        }
        layer.append(VERTICAL)
        return layer.toString()
    }

    private fun getHorizontalCorridorLayer(x: Int, y: Int): String {
        val room = getTile(Point2D(x, y), Room.empty())

        val layer = StringBuilder()
        when {
            room.doors.contains(LEFT) -> layer.append(VERTICAL_DOOR)
            else -> layer.append(VERTICAL)
        }

        if (Point2D(x, y) == droidPosition) {
            layer.append(" ".repeat(5)).append(MiscGlyphs.DROID).append(" ".repeat(5))
        } else {
            layer.append(" ".repeat(12))
        }

        when {
            room.doors.contains(RIGHT) -> layer.append(VERTICAL_DOOR)
            else -> layer.append(VERTICAL)
        }

        return layer.toString()
    }

    private fun getFirstItemLayer(x: Int, y: Int): String {
        val room = getTile(Point2D(x, y), Room.empty())

        val layer = StringBuilder(VERTICAL.toString())
        if (room.hasItems()) {
            val item = room.items.first()
            val itemNameLength = item.name.length
            if (itemNameLength <= 12) {
                val leftPadding = (12 - itemNameLength) / 2
                val rightPadding = 12 - leftPadding - itemNameLength
                layer.append(" ".repeat(leftPadding)).append(item.name).append(" ".repeat(rightPadding))
            } else {
                layer.append(item.name.substring(0, 9)).append("...")
            }
        } else {
            layer.append(" ".repeat(12))
        }
        layer.append(VERTICAL)
        return layer.toString()
    }


    private fun getTopLeftCorner(pos: Point2D): CornerGlyph = when {
        pos.hasRoom(UP) && pos.hasRoom(LEFT) && pos.shift(LEFT).hasRoom(UP) -> CROSS_SECTION
        pos.hasRoom(UP) && pos.hasRoom(LEFT) -> TRIPLE_LEFT
        pos.hasRoom(UP) -> TRIPLE_RIGHT
        pos.hasRoom(LEFT) -> TRIPLE_DOWN
        else -> TOP_LEFT_CORNER
    }

    private fun getTopRightCorner(pos: Point2D): CornerGlyph = when {
        pos.hasRoom(UP) && pos.hasRoom(RIGHT) && pos.shift(RIGHT).hasRoom(UP) -> CROSS_SECTION
        pos.hasRoom(UP) && pos.hasRoom(RIGHT) -> TRIPLE_RIGHT
        pos.hasRoom(UP) -> TRIPLE_LEFT
        pos.hasRoom(RIGHT) -> TRIPLE_DOWN
        else -> TOP_RIGHT_CORNER
    }

    private fun getBottomLeftCorner(pos: Point2D): CornerGlyph = when {
        pos.hasRoom(DOWN) && pos.hasRoom(LEFT) && pos.shift(LEFT).hasRoom(DOWN) -> CROSS_SECTION
        pos.hasRoom(DOWN) && pos.hasRoom(LEFT) -> TRIPLE_LEFT
        pos.hasRoom(DOWN) -> TRIPLE_RIGHT
        pos.hasRoom(LEFT) -> TRIPLE_UP
        else -> BOTTOM_LEFT_CORNER
    }

    private fun getBottomRightCorner(pos: Point2D): CornerGlyph = when {
        pos.hasRoom(DOWN) && pos.hasRoom(RIGHT) && pos.shift(RIGHT).hasRoom(DOWN) -> CROSS_SECTION
        pos.hasRoom(DOWN) && pos.hasRoom(RIGHT) -> CROSS_SECTION
        pos.hasRoom(DOWN) -> TRIPLE_LEFT
        pos.hasRoom(RIGHT) -> TRIPLE_UP
        else -> BOTTOM_RIGHT_CORNER
    }

    private fun getTop(room: Room): String {
        if (room.doors.contains(UP)) {
            val edge = HORIZONTAL.toString().repeat(4)
            return edge + HORIZONTAL_DOOR + edge
        }
        return HORIZONTAL.toString().repeat(12)
    }

    private fun getBottom(room: Room): String {
        if (room.doors.contains(DOWN)) {
            val edge = HORIZONTAL.toString().repeat(4)
            return edge + HORIZONTAL_DOOR + edge
        }
        return HORIZONTAL.toString().repeat(12)
    }

    private enum class MiscGlyphs(val glyph: String) {
        DROID("@D");

        override fun toString(): String = glyph
    }

    private enum class CornerGlyph(val glyph: String) {
        CROSS_SECTION("┼"),
        TRIPLE_LEFT("┤"),
        TRIPLE_RIGHT("├"),
        TRIPLE_DOWN("┬"),
        TRIPLE_UP("┴"),
        TOP_LEFT_CORNER("┌"),
        TOP_RIGHT_CORNER("┐"),
        BOTTOM_LEFT_CORNER("└"),
        BOTTOM_RIGHT_CORNER("┘");

        override fun toString(): String = glyph
    }

    private enum class EdgeGlyph(val glyph: String) {
        HORIZONTAL("─"),
        VERTICAL("│"),
        HORIZONTAL_DOOR("    "),
        VERTICAL_DOOR(" ");

        override fun toString(): String = glyph
    }
}