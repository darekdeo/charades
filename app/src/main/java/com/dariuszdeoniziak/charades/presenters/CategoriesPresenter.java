package com.dariuszdeoniziak.charades.presenters;

import android.app.Activity;
import android.content.SharedPreferences;

import com.dariuszdeoniziak.charades.activities.views.CategoriesView;

import javax.inject.Inject;

public class CategoriesPresenter extends BasePresenter {

    private CategoriesView view;
    private SharedPreferences preferences;

    @Inject
    public CategoriesPresenter(Activity activity, SharedPreferences preferences) {
        this.view = (CategoriesView) activity;
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.edit().putBoolean("ALIVE_TEST", true).apply();
    }

    @Override
    public void onTakeView() {
        // Prove  this unbeliever programmer that it is alive.
        if (preferences.getBoolean("ALIVE_TEST", false))
            view.showToast("View is alive!");
    }

    @Override
    public void onDropView() {
        view.showToast("View is dying!");
        view = null;
    }
}
