package com.aoc.password.strategy

import com.aoc.password.Password
import com.aoc.password.PasswordUtility

/**
 * Initially the Elves remembered a few key facts about the validation criteria of a [Password].
 *
 * - It is a six-digit number
 * - The value is within the range given by the puzzle input
 * - Two adjacent digits are the same (E.g. 22 in 122345)
 * - Going from left to right, the digits never decrease; they only ever increase or stay the same (E.g 111123)
 *
 * The [InitialValidation] strategy checks to see if the given [Password] meets the aforementioned criteria.
 */
class InitialValidation: PasswordValidationStrategy {
    private val utility = PasswordUtility()

    override fun validate(password: Password): Boolean {
        val value = password.value

        if (!utility.hasAdjacentDigits(value) || !utility.hasOnlyAscendingCharacters(value)) return false

        return true
    }
}