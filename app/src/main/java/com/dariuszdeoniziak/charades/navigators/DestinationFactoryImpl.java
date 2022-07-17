package com.dariuszdeoniziak.charades.navigators;

import com.dariuszdeoniziak.charades.App;

public class DestinationFactoryImpl implements DestinationFactory {

    @Override
    public <T> T create(Class<T> clazz) {
        return App.getInstance().feather.instance(clazz);
    }
}
