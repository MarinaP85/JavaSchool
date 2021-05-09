package com.plugin2;

import com.sber.Plugin;

public class MyPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("Плагин номер 2");
    }
}
