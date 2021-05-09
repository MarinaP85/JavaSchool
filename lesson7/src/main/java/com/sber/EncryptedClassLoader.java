package com.sber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final String pluginRootDirectory;

    public EncryptedClassLoader(String key, String dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.pluginRootDirectory = dir;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytesClass = new byte[0];
        File fileClass = new File(pluginRootDirectory + "\\" + name.replace('.', '\\').concat(".class"));
        if (!fileClass.isFile())
            throw new ClassNotFoundException("Не найден класс " + name);

        FileDecoder.decodeFile(fileClass, key);

        try {
            bytesClass = Files.readAllBytes(fileClass.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytesClass, 0, bytesClass.length);
    }


}

