package com.sber.service;

import com.sber.cache.Cache;
import com.sber.cache.CacheType;

import java.util.List;

public interface Service {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true, listSize = 200)
    List<String> run(String item, double value);

    @Cache(cacheType = CacheType.IN_MEMORY)
    String work(String item);

}
