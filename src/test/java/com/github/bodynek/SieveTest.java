package com.github.bodynek;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class SieveTest {


    /**
     * Check first few numbers
     */
    @Test
    public void checkForLowNumbers() {
        Sieve sieve = new Sieve(BigInteger.valueOf(13));
        Assert.assertFalse("-1", sieve.isPrime(-1));
        Assert.assertFalse("0", sieve.isPrime(0));
        Assert.assertFalse("1", sieve.isPrime(1));
        Assert.assertTrue("2", sieve.isPrime(2));
        Assert.assertTrue("3", sieve.isPrime(3));
        Assert.assertFalse("4", sieve.isPrime(4));
        Assert.assertTrue("5", sieve.isPrime(5));
        Assert.assertFalse("6", sieve.isPrime(6));
        Assert.assertTrue("7", sieve.isPrime(7));
        Assert.assertFalse("8", sieve.isPrime(8));
        Assert.assertFalse("9", sieve.isPrime(9));
        Assert.assertFalse("10", sieve.isPrime(10));
        Assert.assertTrue("11", sieve.isPrime(11));
        Assert.assertFalse("12", sieve.isPrime(12));
        Assert.assertTrue("13", sieve.isPrime(13));
    }

    /**
     * Check 981–1000th primes
     * from https://en.wikipedia.org/wiki/List_of_prime_numbers
     */
    @Test
    public void checkForMidPrimes() {
        Sieve sieve = new Sieve(BigInteger.valueOf(7919));
        Assert.assertTrue(sieve.isPrime(7727));
        Assert.assertTrue(sieve.isPrime(7741));
        Assert.assertTrue(sieve.isPrime(7753));
        Assert.assertTrue(sieve.isPrime(7757));
        Assert.assertTrue(sieve.isPrime(7759));
        Assert.assertTrue(sieve.isPrime(7789));
        Assert.assertTrue(sieve.isPrime(7793));
        Assert.assertTrue(sieve.isPrime(7817));
        Assert.assertTrue(sieve.isPrime(7823));
        Assert.assertTrue(sieve.isPrime(7829));
        Assert.assertTrue(sieve.isPrime(7841));
        Assert.assertTrue(sieve.isPrime(7853));
        Assert.assertTrue(sieve.isPrime(7867));
        Assert.assertTrue(sieve.isPrime(7873));
        Assert.assertTrue(sieve.isPrime(7877));
        Assert.assertTrue(sieve.isPrime(7879));
        Assert.assertTrue(sieve.isPrime(7883));
        Assert.assertTrue(sieve.isPrime(7901));
        Assert.assertTrue(sieve.isPrime(7907));
        Assert.assertTrue(sieve.isPrime(7919));
    }

    /**
     * Check first several Mersenne primes
     * from https://en.wikipedia.org/wiki/Mersenne_prime
     */
    @Test
    public void checkForMersennePrimes() {
        Sieve sieve = new Sieve(BigInteger.valueOf(2147483647L * 2147483647L));
        Assert.assertTrue(sieve.isPrime(3));
        Assert.assertTrue(sieve.isPrime(7));
        Assert.assertTrue(sieve.isPrime(31));
        Assert.assertTrue(sieve.isPrime(127));
        Assert.assertTrue(sieve.isPrime(8191));
        Assert.assertTrue(sieve.isPrime(131071));
        Assert.assertTrue(sieve.isPrime(524287));
        Assert.assertTrue(sieve.isPrime(2147483647));
        Assert.assertTrue(sieve.isPrime(2305843009213693951L));
        Assert.assertFalse(sieve.isPrime(2147483647L * 2147483647L));
    }
}