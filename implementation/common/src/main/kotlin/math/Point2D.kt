package math

data class Point2D(val x: Int, val y: Int) {

    fun adjacentPoints() = setOf(Point2D(x, y + 1), Point2D(x + 1, y), Point2D(x, y - 1), Point2D(x - 1, y))

    override fun toString() = "($x, $y)"
}