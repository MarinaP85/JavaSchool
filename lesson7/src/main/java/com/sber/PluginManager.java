package com.sber;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) {
        Plugin newPlugin = null;
        URL urlPlugin;
        try {
            urlPlugin = new File(pluginRootDirectory + "\\" + pluginName).toURI().toURL();
            MyPluginLoader pluginLoader = new MyPluginLoader(new URL[]{urlPlugin},
                    ClassLoader.getSystemClassLoader());
            newPlugin = (Plugin) pluginLoader.loadClass(pluginClassName).newInstance();
        } catch (ClassNotFoundException e) {
            System.err.println("Плагин не найден");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.err.println("Неверно задан URL-адрес");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("Ошибка доступа");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Ошибка при попытке создания объекта");
            e.printStackTrace();
        }
        return newPlugin;
    }

}
