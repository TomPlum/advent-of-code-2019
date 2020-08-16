package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = HashSet<Key>()

    fun add(key: Key) = cache.add(key)

    fun get(key: Key): Key? {
        val found = cache.find { it == key }
        if (found != null) AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
        return found
    }

    fun contains(key: Key): Boolean = cache.contains(key)
}