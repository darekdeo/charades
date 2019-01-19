package com.dariuszdeoniziak.charades.modules;

import com.dariuszdeoniziak.charades.App;

import java.lang.ref.WeakReference;


public class AppModule {

    private WeakReference<App> appRef;

    public AppModule(App app) {
        this.appRef = new WeakReference<>(app);
    }
}
