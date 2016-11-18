package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.codejargon.feather.Provides;

import javax.inject.Singleton;

public class ActivityModule {

    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }
}
