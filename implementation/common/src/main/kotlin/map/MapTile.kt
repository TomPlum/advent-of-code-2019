package map

//TODO: Add abstract methods for isTraversable(), isWall() etc.. for common path-finding functions
open class MapTile<T>(open val value: T) {
    override fun toString() = value.toString()
}