package com.sber;

import java.net.URL;
import java.net.URLClassLoader;

public class MyPluginLoader extends URLClassLoader {
    public MyPluginLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String URL) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(URL);
        if (loadedClass == null) {
            try {
                loadedClass = findClass(URL);
            } catch (ClassNotFoundException e) {
                loadedClass = super.loadClass(URL);
            }
        }
        return loadedClass;
    }
}
