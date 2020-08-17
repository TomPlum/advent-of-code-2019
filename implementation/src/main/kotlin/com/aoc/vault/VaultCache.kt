package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = HashMap<Key, Key>()

    fun add(key: Key) = cache.put(key, key)

    fun get(key: Key): Key? {
        val found = cache[key]
        if (found != null) AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
        return found
    }

    fun contains(key: Key): Boolean = cache.contains(key)
}