package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.modules.AppModule;

import org.codejargon.feather.Feather;


public class App extends Application {

    private static final String TAG = App.class.getName();

    private static App instance = null;

    Feather feather;

    public static App i() {
        return getInstance();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        feather = Feather.with(new AppModule(this));
        feather.injectFields(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        feather = null;
    }
}
