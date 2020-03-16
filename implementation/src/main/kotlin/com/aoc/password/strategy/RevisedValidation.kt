package com.aoc.password.strategy

import com.aoc.password.Password
import com.aoc.password.PasswordUtility

/**
 * An Elf remember an extra, important detail about the [Password] validation.
 *
 * -The two adjacent matching digits must not be part of a larger group of matching digits.
 *
 * The [RevisedValidation] strategy enforces the same criteria as the [InitialValidation] but also
 * enforces the above extra criterion.
 */
class RevisedValidation : PasswordValidationStrategy {
    private val utility = PasswordUtility()

    override fun validate(password: Password): Boolean {
        val value = password.value

        if (!utility.hasAdjacentDigitPair(value) || !utility.hasOnlyAscendingCharacters(value)) return false

        return true
    }

}