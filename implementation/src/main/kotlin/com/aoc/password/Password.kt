package com.aoc.password

import com.aoc.password.strategy.PasswordValidationStrategy

class Password (val value: String) {

    /**
     * Checks if the [Password] has a length of six and conforms to
     * the criteria outlined by the given [PasswordValidationStrategy]
     */
    fun isValid(strategy: PasswordValidationStrategy): Boolean {
        if (value.isEmpty() || value.length != 6) return false

        return strategy.validate(this)
    }

}