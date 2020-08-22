package com.aoc.vault

import com.aoc.log.AdventLogger

class VaultCache {
    private val cache = HashMap<Key, Key>()
    private val linkageCache = HashMap<Char, Map<Key, Float>>()
    private val anotherCache = HashMap<Key, Map<Key, Float>>()

    fun add(key: Key) = cache.put(key, key)

    fun get(key: Key): Key {
        val found = cache[key]
        return if (found != null) {
            AdventLogger.debug("[${this::class.simpleName}] Retrieving Key ${key.name}")
            found
        } else {
            //cache[key] = key
          /*  linkageCache[key.name] = key.linkedKeys
            val matching = linkageCache[key.name]
            matching?.forEach { key.linkTo(it.key, it.value) }*/
            key
        }
    }

    fun getRemainingPath(key: Key): Key? {
        return cache.keys.filter { it.name == key.name }.find { it.hasCompletePath() }
    }

    fun getOrNull(key: Key) = cache[key]

    fun entries() = cache.size

    fun contains(key: Key): Boolean = cache.contains(key)
}