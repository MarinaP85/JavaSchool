package com.sber.jitandgs;

import java.util.HashMap;
import java.util.Map;

public class HomeWork13 {
    public static void main(String[] args) {
        //JIT
        //Запустить с опцией -XX:+PrintCompilation
        //Запустить с опцией -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
        Map<Integer, String> jitMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            jitMap.put(i, "value" + i);
        }
    }
}
