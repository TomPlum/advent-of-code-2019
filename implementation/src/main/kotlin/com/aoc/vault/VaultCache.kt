package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = mutableListOf<Key>()

    fun add(key: Key) = cache.add(key)

    fun get(key: Key): Key? {
        AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
        return cache.find { it == key }
    }

    fun contains(key: Key): Boolean = cache.contains(key)
}