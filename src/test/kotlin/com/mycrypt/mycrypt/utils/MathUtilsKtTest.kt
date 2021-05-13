package com.mycrypt.mycrypt.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MathUtilsKtTest {

    @Test
    fun generateRandomExponentTest() {
        repeat(1000) {
            val f = (1..10000).random()
            val e = generateRandomExponent(f)
            generateRandomExponentTest(e, f)
        }
    }

    private fun generateRandomExponentTest(e: Int, f: Int) {
        assertTrue(gcd(e, f) == 1)
    }

    @Test
    fun generateTwoRandomPrimeNumbersTest() {
        repeat(10000) {
            val (p1, p2) = generateTwoRandomPrimeNumbers(1, 10000)
            println("$p1, $p2")
            generateTwoRandomPrimeNumbersTest(p1, p2)
        }
    }

    private fun generateTwoRandomPrimeNumbersTest(p1: Int, p2: Int) {
        assertTrue(isPrime(p1))
        assertTrue(isPrime(p2))
        val n = (p1 * p2).toString()
        assertTrue(n.length % 2 == 0)
        assertTrue(n.substring(0, 2).toInt() >= 65)
    }

    @Test
    fun isPrimeTestForCommons() {
        assertFalse(isPrime(1))
        assertFalse(isPrime(4))
        assertFalse(isPrime(6))

        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(5))
    }

    @Test
    fun inverseTest() {
        repeat(10000) {
            val a = (1..10000).random()
            val root = (1..10000).random()
            if (gcd(a, root) == 1) {
                println("a: $a, root: $root")
                inverseTest(a, root, inverse(a, root))
            }
        }
    }

    private fun inverseTest(a: Int, root: Int, inverse: Int) {
        assertEquals(1, (a * inverse) % root)
    }
}
