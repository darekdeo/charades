package com.dariuszdeoniziak.charades.activities.presenters;

import android.app.Activity;
import android.content.SharedPreferences;

import com.dariuszdeoniziak.charades.activities.views.CategoriesActivityView;

import javax.inject.Inject;

public class ActivityCategoriesPresenter extends BasePresenter {

    private CategoriesActivityView view;
    private SharedPreferences preferences;

    @Inject
    public ActivityCategoriesPresenter(Activity activity, SharedPreferences preferences) {
        this.view = (CategoriesActivityView) activity;
        this.preferences = preferences;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView() {
        // Prove  this unbeliever programmer that it is alive.
        view.showToast("View is alive!");
    }

    @Override
    public void onDropView() {
        view.showToast("View is dying!");
        view = null;
    }
}
