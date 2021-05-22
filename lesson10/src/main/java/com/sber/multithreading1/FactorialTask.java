package com.sber.multithreading1;

public class FactorialTask implements Runnable {
    private final int factorial;

    public FactorialTask(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": !" + factorial + " = " +
                CalcFactorial.factorial(factorial));
    }
}
