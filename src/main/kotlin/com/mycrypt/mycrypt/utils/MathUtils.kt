package com.mycrypt.mycrypt.utils

import kotlin.math.sqrt

fun isPrime(n: Int): Boolean {
    if (n == 1) {
        return false
    }
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            return false
        }
    }
    return true
}

fun gcd(m: Int, n: Int): Int {
    var a = m
    var b = n
    while (a % b > 0) {
        val c = a % b
        a = b
        b = c
    }
    return b
}

fun generateTwoRandomPrimeNumbers(min: Int, max: Int): List<Int> {
    var p1 = generateRandomPrimeNumber(min, max)
    var p2 = generateRandomPrimeNumber(min, max)
    var n = (p1 * p2).toString()
    while (n.length % 2 != 0 || n.substring(0, 2).toInt() < 67) {
        p1 = generateRandomPrimeNumber(min, max)
        p2 = generateRandomPrimeNumber(min, max)
        n = (p1 * p2).toString()
    }
    return listOf(p1, p2)
}

fun generateRandomPrimeNumber(min: Int, max: Int): Int {
    var i = generateRandomVariable(min, max)
    while (!isPrime(i)) {
        i = generateRandomVariable(min, max)
    }
    return i
}

fun generateRandomVariable(min: Int, max: Int): Int {
    return (min..max).random()
}

fun generateRandomExponent(f: Int): Int {
    var e = generateRandomVariable(2, f)
    while (gcd(e, f) != 1) {
        e = generateRandomVariable(2, f)
    }
    return e
}

fun euler(p1: Int, p2: Int): Int {
    return (p1 - 1) * (p2 - 1)
}

// Root 1 olanda deqiq deyil
fun inverse(n: Int, root: Int): Int {
    if (gcd(n, root) != 1) {
        throw IllegalArgumentException()
    }
    var a = n
    var m = root
    var c = 0
    var e = 1
    var d = 1
    var f = 0
    while(m % a != 0) {
        val q = m / a
        val r = m % a

        val tc = c
        val te = e
        c = d
        e = f
        d = tc - q * d
        f = te - q * f

        m = a
        a = r
    }
    if(d < 0) {
        return root + d
    }
    return d
}
