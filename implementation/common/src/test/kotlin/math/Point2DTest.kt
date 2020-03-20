package math

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Point2DTest {
    @Test
    fun adjacentPoints() {
        assertThat(Point2D(0,0).adjacentPoints()).isEqualTo(setOf(Point2D(0,1), Point2D(1,0), Point2D(0,-1), Point2D(-1,0)))
    }
}