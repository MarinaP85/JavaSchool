package com.sber.proxy;

import com.sber.cache.Cache;
import com.sber.cache.CacheType;
import com.sber.service.Service;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CacheProxyHandler implements InvocationHandler {
    private final Service service;
    private final Map<Object, Object> cache;
    private static final String cachePath = "G:\\IdeaProjects\\ForTests\\cache\\";

    public CacheProxyHandler(Service service) {
        this.cache = new HashMap<>();
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Map<Object, Object> cacheTmp;
            //проверяем, если указано, что кэш из файла, десериализуем, иначе берем текущий кэш
            if (method.getDeclaredAnnotation(Cache.class).cacheType() == CacheType.FILE) {
                System.out.println("Кэш из файла");
                if (method.getDeclaredAnnotation(Cache.class).fileName().equals("methodName")) {
                    cacheTmp = deserialize(method.getDeclaredAnnotation(Cache.class).fileNamePrefix() +
                            method.getName());
                } else {
                    cacheTmp = deserialize(method.getDeclaredAnnotation(Cache.class).fileNamePrefix() +
                            method.getDeclaredAnnotation(Cache.class).fileName());
                }
            } else {
                System.out.println("Кэш из памяти");
                cacheTmp = cache;
            }

            //проверяем, содержится ли такой метод с заданными параметрами в кэше
            List<Object> key = new ArrayList<>(Arrays.asList(args));
            key.add(0, method.getName());
            if (!cacheTmp.containsKey(key)) {
                System.out.println("Не из кэша");
                Object result = method.invoke(service, args);
                //если результатом работы метода явлется список, проверяем, не содержит ли
                //он больше элементов, чем задано в аннотации, если больше, в кэш не пишем
                if (method.getReturnType().getTypeName().equals("java.util.List")) {
                    List<?> resultList = (ArrayList<?>) result;
                    int maxSize = method.getDeclaredAnnotation(Cache.class).listSize();
                    if (resultList.size() > maxSize) {
                        System.out.println("В кеше можно хранить List, содержащий не больше " + maxSize + " элементов.");
                        return result;
                    }
                }
                cacheTmp.put(key, result);
                //проверяем, если указано, что кэш из файла, сериализуем
                if (method.getDeclaredAnnotation(Cache.class).cacheType() == CacheType.FILE) {
                    serialize(method, cacheTmp);
                }
            } else {
                System.out.println("Из кэша");
            }
            return cacheTmp.get(key);
        } else {
            return method.invoke(service, args);
        }
    }

    private void serialize(Method method, Map<Object, Object> cache) {
        String fileName = method.getDeclaredAnnotation(Cache.class).fileNamePrefix();
        String path = cachePath;

        if (method.getDeclaredAnnotation(Cache.class).fileName().equals("methodName")) {
            fileName = fileName + method.getName();
        } else {
            fileName = fileName + method.getDeclaredAnnotation(Cache.class).fileName();
        }

        path = path + fileName;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(cache);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //если в аннотации указано, что нужно архивировать, дополнительно сериализуем в zip
        if (method.getDeclaredAnnotation(Cache.class).zip()) {
            System.out.println("Дополнительно сериализуем в zip");
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path + ".zip"))) {
                ZipEntry zipName = new ZipEntry(fileName);
                zos.putNextEntry(zipName);
                ObjectOutputStream oos = new ObjectOutputStream(zos);
                oos.writeObject(cache);
                zos.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<Object, Object> deserialize(String fileName) {
        String path = cachePath + fileName;

        Map<Object, Object> cacheTmp = new HashMap<>();

        if (!new File(path).exists()) {
            System.out.println("Файла не существует, создаем новый кэш");
            return cacheTmp;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            cacheTmp = (HashMap<Object, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cacheTmp;
    }
}
