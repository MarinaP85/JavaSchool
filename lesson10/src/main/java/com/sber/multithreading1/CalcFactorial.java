package com.sber.multithreading1;

import java.math.BigInteger;

public class CalcFactorial {
    public static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}
