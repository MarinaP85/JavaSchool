package ru.plugin1;

import ru.sbt.Plugin;

public class MyPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Плагин номер 1");
    }
}
