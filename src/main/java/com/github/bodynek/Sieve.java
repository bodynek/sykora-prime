/*
 * Wultra Antivirus Server and Related Components
 * Copyright (c) 2021, Wultra s.r.o. (www.wultra.com).
 *
 * All rights reserved. This source code can be used only for purposes specified
 * by the given license contract signed by the rightful deputy of Wultra s.r.o.
 * This source code can be used only by the owner of the license.
 *
 * Any disputes arising in respect of this agreement (license) shall be brought
 * before the Municipal Court of Prague.
 */
package com.github.bodynek;

import java.math.BigInteger;
import java.util.BitSet;

public class Sieve {

    private final BitSet sieveStorage;

    /**
     * Prepare Euler's sieve using BitSet for primeness test to maximum size BitSet can hold
     */
    public Sieve() {
        this(new BigInteger("42000000000000000000"));
    }

    /**
     * Prepare Euler's sieve using BitSet for primeness test to size size
     *
     * @param size maximum number to be checked
     */
    public Sieve(BigInteger size) {
        int sieveSize;
        try {
            sieveSize = size.sqrt().divide(BigInteger.valueOf(3)).intValueExact();
        } catch (ArithmeticException e) {
            sieveSize = Integer.MAX_VALUE;
        }
        sieveStorage = new BitSet(sieveSize);
        sieveStorage.set(0, sieveStorage.size() - 1);
        long count = 0;
        for (int number = 0; number < sieveStorage.size() - 1; number++) {
            if (sieveStorage.get(number)) {
                final long finalNumber = toValue(number);
                BitSet bitset = sieveStorage.get(0, toIndex(toValue(sieveStorage.size() - 1) / finalNumber));
                if (bitset.stream()
                        .skip(count)
                        .peek(index -> {
                            sieveStorage.clear(toIndex(finalNumber * toValue(index)));
                        }).count() == 0) {
                    break;
                }
                count++;
            }
        }
    }

    /**
     * Check number for primeness using Euler's sieve
     * <p>
     * Using modulo 6 approach for speeding-up. Works only for divisors greater than 4!
     *
     * @param number number to be checked for primeness
     * @return true if number is prime
     */
    protected boolean isPrimeBySieve(BigInteger number) {
        if (number.compareTo(BigInteger.valueOf(toValue(sieveStorage.size() - 1))) <= 0) {
            return sieveStorage.get(toIndex(number.longValue()));
        }
        boolean ret = sieveStorage.stream()
                .mapToObj(index -> {
                    if ((toValue(index) * toValue(index)) > number.longValue()) {
                        return true;
                    }
                    return number.remainder(BigInteger.valueOf(toValue(index)))
                            .compareTo(BigInteger.ZERO) != 0;
                })
                .reduce(Boolean::logicalAnd)
                .orElse(true);
        BigInteger last = BigInteger.valueOf(toValue(sieveStorage.size() - 1));
        if (!ret || number.compareTo(last.pow(2)) < 0) {
            return ret;
        }
        return isPrimeByDivisionHelpedBySieve(number, last);
    }

    private int toIndex(long value) {
        return (int) ((value / 3) - 1);
    }

    private long toValue(int index) {
        return ((long) index) * 3 + ((index % 2) == 0 ? 5 : 4);
    }

    /**
     * Check number for primeness using division
     *
     * @param number number to be checked for primeness
     * @param from   smallest divisor to be used for check
     * @return true if number is prime
     */
    protected boolean isPrimeByDivisionHelpedBySieve(BigInteger number, BigInteger from) {
        BigInteger max = number.sqrt();
        BigInteger i;
        for (i = from; i.compareTo(max) <= 0; i = i.add(BigInteger.TWO)) {
            if (!isPrime(i)) {
                continue;
            }
            if (number.remainder(i).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check number for primeness using division
     *
     * @param number number to be checked for primeness
     * @param from   smallest divisor to be used for check
     * @return true if number is prime
     */
    protected boolean isPrimeByDivision(BigInteger number, BigInteger from) {
        BigInteger max = number.sqrt();
        BigInteger i;
        for (i = from; i.compareTo(max) <= 0; i = i.add(BigInteger.TWO)) {
            if (number.remainder(i).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if number is prime
     * <p>
     * Using modulo 6 approach for speed-up
     *
     * @param number number to be checked for primeness
     * @return true if number is prime
     */
    public boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        if (number.compareTo(BigInteger.valueOf(4)) < 0) {
            return true;
        }
        if (number.remainder(BigInteger.valueOf(6)).compareTo(BigInteger.ONE) != 0
                && number.remainder(BigInteger.valueOf(6)).compareTo(BigInteger.valueOf(5)) != 0) {
            return false;
        }
        return isPrimeBySieve(number);
    }

    /**
     * Return true if number is prime
     *
     * @param number number to be checked for primeness
     * @return true if number is prime
     */
    public boolean isPrime(long number) {
        return isPrime(BigInteger.valueOf(number));
    }

}
