package ru.plugin2;

import ru.sbt.Plugin;

public class MyPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Плагин номер 2");
    }
}
