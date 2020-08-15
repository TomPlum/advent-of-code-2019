package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache(val totalKeyQuantity: Int) {
    private val cache = mutableListOf<Key>()

    fun add(key: Key) {
        if (key.collectedKeysQuantity() == totalKeyQuantity ) AdventLogger.debug("Found Path ${key.pathString()}")
        cache.add(key)
    }

    fun get(key: Key): Key? {
        /*val keysWithSameName = cache.filter { it.name == key.name }
        if (keysWithSameName.isNotEmpty()) {
            AdventLogger.debug("There are ${keysWithSameName.size} key states for ${keysWithSameName.first().name}...")
            AdventLogger.debug("The source key has ${key.collectedKeys}")
            AdventLogger.debug("The cached keys have;")
            keysWithSameName.forEach {
                AdventLogger.debug(it.collectedKeys)
            }
        }*/
        val found = cache.find {
            it.name == key.name && it.pos == key.pos && it.collectedKeys.map { it.name } == key.collectedKeys.map { it.name }
        }
        if (found != null) {
            AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
        }
        return found
    }

    fun contains(key: Key): Boolean = cache.contains(key)
}