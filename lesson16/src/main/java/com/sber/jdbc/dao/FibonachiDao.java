package com.sber.jdbc.dao;

import java.sql.Connection;
import java.util.List;

public interface FibonachiDao {
    List<Integer> getFibonachi(int n);

    List<Integer> getAllFibonachi();

    boolean addFibonachi(List<Integer> initList, int beginN);
}
