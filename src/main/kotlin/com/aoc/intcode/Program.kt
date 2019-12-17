package com.aoc.intcode

class Program private constructor(actionString: String) {

    var currentActionType = AddressType.OPCODE
    var memory: Memory

    companion object {
        fun from(actionString: String): Program = Program(actionString)
    }

    override fun toString(): String {
        return memory.addresses.joinToString(",", postfix = "") { it.toString() }
    }

    fun updateNextActionType() {
        currentActionType = when (currentActionType) {
            AddressType.OPCODE -> AddressType.FIRST_INPUT
            AddressType.FIRST_INPUT -> AddressType.SECOND_INPUT
            AddressType.SECOND_INPUT -> AddressType.OUTPUT
            AddressType.OUTPUT -> AddressType.OPCODE
        }
    }

    private fun parseMemoryAddresses(actions: String): MutableList<Int> = actions.split(",").map { it.toInt() }.toMutableList()

    init {
        this.memory = Memory(parseMemoryAddresses(actionString))
    }

}