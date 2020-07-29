package com.aoc.intcode.droid.cryo

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class InventoryTest {

    @Nested
    inner class AddItem {
        @Test
        fun addSingleItem() {
            val inventory = Inventory()
            inventory.add(Item("green ball"))
            assertThat(inventory.take(Item("green ball"))).isEqualTo(Item("green ball"))
        }

        @Test
        fun addMultipleItems() {
            val inventory = Inventory()
            inventory.add(Item("green ball"))
            inventory.add(Item("blue ball"))
            assertThat(inventory.take(Item("green ball"))).isEqualTo(Item("green ball"))
        }
    }

    @Nested
    inner class TakeItem {
        @Test
        fun takeWhenInventoryContainsOneItem() {
            val inventory = Inventory()
            inventory.add(Item("green ball"))
            assertThat(inventory.take(Item("green ball"))).isEqualTo(Item("green ball"))
        }

        @Test
        fun takeWhenInventoryContainsMultipleItems() {
            val inventory = Inventory()
            inventory.add(Item("green ball"))
            inventory.add(Item("blue ball"))
            assertThat(inventory.take(Item("green ball"))).isEqualTo(Item("green ball"))
        }

        @Test
        fun takeWhenInventoryIsEmpty() {
            assertThat(Inventory().take(Item("green ball"))).isNull()
        }
    }

}
