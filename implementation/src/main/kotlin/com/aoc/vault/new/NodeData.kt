package com.aoc.vault.new

data class NodeData<T>(val key: T, val weight: Long, val doors: Set<T>, val keys: Set<T>)