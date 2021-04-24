package com.github.bodynek;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SieveTest {

    public Sieve sieve;

    @Before
    public void setUp() {
        sieve = new Sieve();
    }

    /**
     * Check first few numbers
     */
    @Test
    public void checkForLowNumbers() {
        Assert.assertFalse(sieve.isPrime(-1));
        Assert.assertFalse(sieve.isPrime(0));
        Assert.assertFalse(sieve.isPrime(1));
        Assert.assertTrue(sieve.isPrime(2));
        Assert.assertTrue(sieve.isPrime(3));
        Assert.assertFalse(sieve.isPrime(4));
    }

    /**
     * Check 981–1000th primes
     * from https://en.wikipedia.org/wiki/List_of_prime_numbers
     */
    @Test
    public void checkForHigherPrimes() {
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
}