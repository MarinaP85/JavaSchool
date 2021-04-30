package ru.sbt;

public class MyPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Плагин изначальный");
    }
}
