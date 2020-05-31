package com.aoc.vault

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class VaultTileTest {

    @Test
    fun isEntrancePositive() {
        assertThat(VaultTile('@').isEntrance()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a', 'E', '#', '.'])
    fun isEntranceNegative(value: Char) {
        assertThat(VaultTile(value).isEntrance()).isFalse()
    }

    @Test
    fun isWallPositive() {
        assertThat(VaultTile('#').isWall()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a', 'E', '@', '.'])
    fun isWallNegative(value: Char) {
        assertThat(VaultTile(value).isWall()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a','b','c','x','y','z'])
    fun isKeyPositive(value: Char) {
        assertThat(VaultTile(value).isKey()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['A', 'E', '@', '.', '#'])
    fun isKeyNegative(value: Char) {
        assertThat(VaultTile(value).isKey()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(chars = ['A','B','C','X','Y','Z'])
    fun isDoorPositive(value: Char) {
        assertThat(VaultTile(value).isDoor()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a','.','#','@','b','-'])
    fun isDoorNegative(value: Char) {
        assertThat(VaultTile(value).isDoor()).isFalse()
    }

    @Test
    fun isTraversablePositive() {
        assertThat(VaultTile('.').isTraversable()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(chars = ['a','A','#','@','b','-'])
    fun isTraversableNegative(value: Char) {
        assertThat(VaultTile(value).isTraversable()).isFalse()
    }
}