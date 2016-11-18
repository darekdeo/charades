package com.dariuszdeoniziak.charades.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dariuszdeoniziak.charades.App;

import org.codejargon.feather.Provides;

import javax.inject.Singleton;

public class AppModule {

    App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}
