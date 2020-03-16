package math

class Formulae {
    companion object {
        fun lcm(input: List<Long>): Long {
           var result = input[0]
            input.forEachIndexed { i, _ -> result = lcm(result, input[i]) }
            return result
        }

        private fun gcd(a: Long, b: Long): Long {
            var n1 = a
            var n2 = b
            while (n1 != n2) {
                if (n1 > n2) n1 -= n2 else n2 -= n1
            }
            return n1
        }

        private fun lcm(a: Long, b: Long) = a * (b / gcd(a, b))
    }


}