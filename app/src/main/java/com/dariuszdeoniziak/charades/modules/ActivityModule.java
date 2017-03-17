package com.dariuszdeoniziak.charades.modules;

import android.app.Activity;
import android.content.Context;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SharedPreferencesInteractor;
import com.dariuszdeoniziak.charades.models.interactors.SugarOrmInteractor;
import com.dariuszdeoniziak.charades.presenters.CategoriesActivityPresenter;
import com.dariuszdeoniziak.charades.views.activities.CategoriesActivity;

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
    @Singleton
    public CategoriesActivityPresenter provideCategoriesPresenter() {
        return new CategoriesActivityPresenter(
                (CategoriesActivity) activity,
                providePreferencesInteractor());
    }

    @Provides
    @Singleton
    public ModelInteractor provideModelInteractor() {
        return new SugarOrmInteractor(activity);
    }

    @Provides
    @Singleton
    public PreferencesInteractor providePreferencesInteractor() {
        return new SharedPreferencesInteractor(activity);
    }
}
