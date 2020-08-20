package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = HashMap<Key, Key>()

    fun add(key: Key) = cache.put(key, key)

    fun get(key: Key): Key {
        val found = cache[key]
        return if (found != null) {
            AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
            found
        } else {
            cache[key] = key
            key
        }
    }

    fun getOrNull(key: Key) = cache[key]

    fun entries() = cache.size

    fun contains(key: Key): Boolean = cache.contains(key)
}