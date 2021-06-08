package com.sber.jdbc.service;

import com.sber.jdbc.cache.Cachable;
import com.sber.jdbc.connection.H2DB;

import java.util.List;

public interface Service {
    @Cachable(H2DB.class)
    List<Integer> fibonachi(List<Integer> initList, int n);

}
