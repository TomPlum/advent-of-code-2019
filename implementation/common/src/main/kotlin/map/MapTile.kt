package map

open class MapTile<T>(open val value: T) {
    override fun toString() = value.toString()
}