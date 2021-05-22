package com.sber.multithreading1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeWork10 {
    public static void main(String[] args) {
        String file;
        try {
            file = new String(Files.readAllBytes(Paths.get("G:\\IdeaProjects\\ForTests\\HomeWork10_test.txt")),
                    "Cp1251");
            String[] str = file.replace("\n\r", " ").split("\\s+");
            for (String s : str) {
                new Thread(new FactorialTask(Integer.parseInt(s))).start();
            }
            System.out.println("Ожидаем вычисление факториала для всех чисел в файле.");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }
}
