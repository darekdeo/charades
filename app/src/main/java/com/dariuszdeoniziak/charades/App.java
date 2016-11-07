package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.components.AppComponent;
import com.dariuszdeoniziak.charades.components.DaggerAppComponent;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.modules.DbModule;

public class App extends Application {

    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponents();
    }

    void initComponents() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
