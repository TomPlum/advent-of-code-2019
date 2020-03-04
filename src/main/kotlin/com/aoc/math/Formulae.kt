package com.aoc.math

class Formulae {
    companion object {
        fun lcm(ints: List<Int>): Int {
           var result = ints[0]
            ints.forEachIndexed { i, _ -> result = lcm(result, ints[i]) }
            return result
        }

        private fun gcd(a: Int, b: Int): Int {
            var n1 = a
            var n2 = b
            while (n1 != n2) {
                if (n1 > n2) n1 -= n2 else n2 -= n1
            }
            return n1
        }

        private fun lcm(a: Int, b: Int) = a * (b / gcd(a, b))
    }


}