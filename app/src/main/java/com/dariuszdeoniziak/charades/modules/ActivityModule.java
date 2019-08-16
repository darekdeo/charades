package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.Context;

import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;
import com.dariuszdeoniziak.charades.data.datasources.sharedpreferences.SharedPreferencesDataSource;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;


public class ActivityModule {

    private final WeakReference<Activity> activityRef;

    public ActivityModule(Activity activity) {
        this.activityRef = new WeakReference<>(activity);
    }

    @Provides
    @Singleton
    public Activity provideActivity() {
        return activityRef.get();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return activityRef.get();
    }

    @Provides
    @Singleton
    public PreferencesDataSource providePreferencesInteractor() {
        return new SharedPreferencesDataSource(activityRef.get());
    }
}
