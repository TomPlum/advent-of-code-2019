package com.aoc.intcode.droid.cryo.droid

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.aoc.Day
import com.aoc.droid.cryo.droid.TestDroidLogger
import com.aoc.input.InputReader
import com.aoc.intcode.droid.cryo.command.DropCommand
import com.aoc.intcode.droid.cryo.command.InventoryCommand
import com.aoc.intcode.droid.cryo.command.MovementCommand
import com.aoc.intcode.droid.cryo.command.TakeCommand
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CryostasisDroidTest {

    private val input = InputReader.read<String>(Day(25)).asSingleString()
    private val droid = CryostasisDroid(input)
    private val logger = TestDroidLogger()

    @BeforeEach
    internal fun setUp() {
        droid.boot()
        droid.logger = logger
    }

    @Nested
    inner class Commands {
        @Nested
        inner class ViewInventory {
            @Test
            fun viewInventory() {
                droid.command(InventoryCommand())
                assertThat(logger.logs).contains("Inventory: Empty")
            }
        }

        @Nested
        inner class Drop {
            @Test
            fun dropItemInInventoryShouldRemoveFromInventory() {
                droid.command(MovementCommand("south"))
                droid.command(TakeCommand("festive hat"))
                droid.command(DropCommand("festive hat"))
                assertThat(droid.inventory.getAllItems()).isEmpty()
            }

            @Test
            fun dropItemNotInInventory() {
                droid.command(DropCommand("banana"))
                assertThat(logger.logs).contains("You do not have a banana in your inventory!")
            }
        }

        @Nested
        inner class Take {
            @Test
            fun takeItemShouldAddToInventory() {
                droid.command(MovementCommand("south"))
                droid.command(TakeCommand("festive hat"))
                assertThat(droid.inventory.getAllItems()).containsOnly(Item("festive hat"))
            }

            @Test
            fun takeItemNotInRoom() {
                droid.command(MovementCommand("south"))
                droid.command(TakeCommand("banana"))
                assertThat(logger.logs).contains("There is no banana in the Passages!")
            }
        }

        @Nested
        inner class Movement {
            @Test
            fun moveToInvalidDirection() {
                droid.command(MovementCommand("north"))
                assertThat(logger.logs).contains("There is no room to the UP")
            }
        }
    }

}