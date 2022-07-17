package com.dariuszdeoniziak.charades.navigators;

public interface DestinationFactory {

    <T> T create(Class<T> clazz);
}
