package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.Context;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SharedPreferencesInteractor;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;


public class ActivityModule {

    private WeakReference<Activity> activityRef;

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
    public PreferencesInteractor providePreferencesInteractor() {
        return new SharedPreferencesInteractor(activityRef.get());
    }
}
