package map

open class MapTile<T>(open val value: T) {
    companion object {
        fun unknown() = MapTile
    }

    override fun toString() = value.toString()
}