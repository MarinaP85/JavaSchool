package com.sber.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value) {
        return new ArrayList<>(Arrays.asList(item, Double.toString(value), new Date().toString()));
    }

    @Override
    public String work(String item) {
        return item + new Date().toString();
    }
}
