package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache: MutableMap<Char, Key> = mutableMapOf()

    fun add(key: Key) = cache.put(key.name, key)

    fun get(key: Key): Key? {
        val found = cache[key.name]
        if (found != null) {
            AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
        }
        return found
    }

    fun contains(key: Key): Boolean = cache.contains(key.name)
}