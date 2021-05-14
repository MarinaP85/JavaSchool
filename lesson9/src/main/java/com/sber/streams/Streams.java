package com.sber.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Streams<T> {
    private List<T> streamList;

    private Streams(List<T> list) {
        streamList = new ArrayList<>(list);
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (T streamListElement : streamList) {
            if (predicate.test(streamListElement)) {
                resultList.add(streamListElement);
            }
        }
        streamList = resultList;
        return this;
    }

    public Streams<T> transform(UnaryOperator<T> unaryOperator) {
        List<T> resultList = new ArrayList<>();
        for (T streamListElement : streamList) {
            resultList.add(unaryOperator.apply(streamListElement));
        }
        streamList = resultList;
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> key, Function<? super T, ? extends V> value) {
        Map<K, V> resultMap = new HashMap<>();
        for (T streamListElement : streamList) {
            resultMap.put(key.apply(streamListElement), value.apply(streamListElement));
        }
        return resultMap;
    }

}
