package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SugarOrmInteractor;

import org.codejargon.feather.Provides;

import javax.inject.Singleton;

public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public Activity provideActivity() {
        return activity;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return activity;
    }

    @Provides
    public ModelInteractor provideModelInteractor() {
        return new SugarOrmInteractor();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(activity);
    }
}
