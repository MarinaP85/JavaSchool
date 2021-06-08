package com.sber.jdbc.proxy;

import com.sber.jdbc.cache.Cachable;
import com.sber.jdbc.connection.Source;
import com.sber.jdbc.dao.FibonachiDao;
import com.sber.jdbc.dao.FibonachiDaoImpl;
import com.sber.jdbc.service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CacheProxyHandler implements InvocationHandler {
    private final Service service;

    public CacheProxyHandler(Service service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Method dbMethod;
        Object[] argsTmp = new Object[2];
        List<Integer> initList = new ArrayList<>();
        int n = 0;
        if (args.length > 1) {
            n = (int) args[1];
        }
        if (method.isAnnotationPresent(Cachable.class)) {
            Class<?> clazz = method.getDeclaredAnnotation(Cachable.class).value();
            Source db = (Source) clazz.newInstance();
            //dbMethod = db.getMethod("connection");
            Connection connection = db.connection();
            //Connection connection = (Connection) dbMethod.invoke(method.getDeclaredAnnotation(Cachable.class).value());
            FibonachiDao fibonachiDao = new FibonachiDaoImpl(connection);
            initList = fibonachiDao.getFibonachi(n);
            if (initList.size() <= n) {
                System.out.println("Не из кэша");
                List<Integer> resultList;
                argsTmp[0] = initList;
                argsTmp[1] = n;
                resultList = (List<Integer>) method.invoke(service, argsTmp);
                if (resultList != null) {
                    fibonachiDao.addFibonachi(resultList, initList.size());
                }
                return resultList;
            } else {
                System.out.println("Из кэша");
                return initList;
            }
        } else {
            return method.invoke(service, args);
        }
    }

}
