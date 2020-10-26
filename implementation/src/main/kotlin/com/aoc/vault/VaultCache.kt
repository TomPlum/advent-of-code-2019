package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = HashMap<KeyTuple, Key>()

    fun add(key: Key): Key? {
        return cache.put(createTuple(key), key)
    }

    fun get(key: Key): Key {
        val found = cache[createTuple(key)]
        return if (found != null) {
            AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
            found
        } else {
            key
        }
    }

    fun entries() = cache.size

    fun contains(key: Key): Boolean = cache.contains(createTuple(key))

    private fun createID(key: Key): KeyID {
        return KeyID(key.name, key.collectedKeys.map { it.name })
    }

    private fun createTuple(key: Key): KeyTuple {
        return KeyTuple(key.name, key.steps(), key.collectedKeys.map { it.name })
    }
}