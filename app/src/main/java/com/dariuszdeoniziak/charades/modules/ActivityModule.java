package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.Context;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SharedPreferencesInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SugarOrmInteractor;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;

import org.codejargon.feather.Provides;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

public class ActivityModule {

    private WeakReference<Activity> activity;

    public ActivityModule(Activity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Provides
    @Singleton
    public Activity provideActivity() {
        return activity.get();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return activity.get();
    }

    @Provides
    @Singleton
    public CategoriesActivityPresenter provideCategoriesPresenter() {
        return new CategoriesActivityPresenter(providePreferencesInteractor());
    }

    @Provides
    @Singleton
    public ModelInteractor provideModelInteractor() {
        return new SugarOrmInteractor(activity.get());
    }

    @Provides
    @Singleton
    public PreferencesInteractor providePreferencesInteractor() {
        return new SharedPreferencesInteractor(activity.get());
    }
}
