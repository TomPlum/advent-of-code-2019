package com.aoc.vault

class VaultCache {
    private val cache = mutableListOf<Key>()

    fun add(key: Key) = cache.add(key)

    fun contains(key: Key): Boolean = cache.contains(key)
}