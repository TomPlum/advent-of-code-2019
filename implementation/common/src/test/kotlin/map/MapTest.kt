package map

import org.junit.jupiter.api.Test

class MapTest {
    @Test
    fun example() {
        val map = ExampleMap()
    }

    private class ExampleTile(data: Int) : MapTile<Int>(data) {

    }

    private class ExampleMap : Map<ExampleTile>() {

    }
}