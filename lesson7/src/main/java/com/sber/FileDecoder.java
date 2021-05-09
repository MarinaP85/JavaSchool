package com.sber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileDecoder {
    public static void encodeFile(File file, String key) {
        byte[] byteKey = key.getBytes();
        try {
            byte[] byteFile = Files.readAllBytes(file.toPath());
            for (int i = 0; i < byteFile.length; i++) {
                if (i % 2 == 0) {
                    byteFile[i] = (byte) (byteFile[i] + byteKey[0] - byteKey[byteKey.length - 1]);
                } else {
                    byteFile[i] = (byte) (byteFile[i] + byteKey[1] - byteKey[byteKey.length - 2]);
                }
            }
            Files.write(file.toPath(), byteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeFile(File file, String key) {
        byte[] byteKey = key.getBytes();
        try {
            byte[] byteFile = Files.readAllBytes(file.toPath());
            for (int i = 0; i < byteFile.length; i++) {
                if (i % 2 == 0) {
                    byteFile[i] = (byte) (byteFile[i] - byteKey[0] + byteKey[byteKey.length - 1]);
                } else {
                    byteFile[i] = (byte) (byteFile[i] - byteKey[1] + byteKey[byteKey.length - 2]);
                }
            }
            Files.write(file.toPath(), byteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
