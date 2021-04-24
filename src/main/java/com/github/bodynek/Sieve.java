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

    private BitSet sieve;

    public Sieve() {
        sieve = new BitSet();
    }

    public boolean isPrime(long number) {
        if (number < 2) {
            return false;
        }
        if (number < 4) {
            return true;
        }
        return false;
    }

    public boolean isPrime(BigInteger number) {
        return isPrime(number.longValueExact());
    }
}
