package com.dariuszdeoniziak.charades;

import android.app.Application;

import com.dariuszdeoniziak.charades.components.AppComponent;
import com.dariuszdeoniziak.charades.components.DaggerAppComponent;
import com.dariuszdeoniziak.charades.modules.AppModule;
import com.dariuszdeoniziak.charades.modules.DbModule;
import com.orm.SugarContext;

public class App extends Application {

    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);

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

    @Override
    public void onTerminate() {
        super.onTerminate();

        SugarContext.terminate();
    }
}
