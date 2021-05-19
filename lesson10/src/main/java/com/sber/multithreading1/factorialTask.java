package com.sber.multithreading1;

public class factorialTask implements Runnable {
    private final int factorial;

    public factorialTask(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": !" + factorial + " = " +
                CalcFactorial.factorial(factorial));
    }
}
