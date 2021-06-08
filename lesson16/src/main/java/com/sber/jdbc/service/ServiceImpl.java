package com.sber.jdbc.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<Integer> fibonachi(List<Integer> initList, int n) {
        List<Integer> listResult;
        int beginN = 2;
        if (initList == null) {
            listResult = new ArrayList<>();
        } else {
            listResult = new ArrayList<>(initList);
            if (initList.size() > 2) {
                beginN = initList.size();
            }
        }
        if (listResult.size() == 0) {
            listResult.add(0);
        }
        if ((listResult.size() == 1) && (n > 0)) {
            listResult.add(1);
        }

        for (int i = beginN; i <= n; i++) {
            listResult.add(listResult.get(i - 1) + listResult.get(i - 2));
        }
        return listResult;

    }
}
