package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.components.DaggerDbComponent;
import com.dariuszdeoniziak.charades.components.DbComponent;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.modules.DbModule;

public class App extends Application {

    protected DbComponent mDbComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponents();
    }

    void initComponents() {
        mDbComponent = DaggerDbComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .build();
    }

    public DbComponent getDbComponent() {
        return mDbComponent;
    }
}
