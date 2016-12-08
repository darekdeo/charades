package com.dariuszdeoniziak.charades.activities.presenters;

import android.app.Activity;

import com.dariuszdeoniziak.charades.activities.views.CategoriesActivityView;

import javax.inject.Inject;

public class ActivityCategoriesPresenter extends BasePresenter {

    private CategoriesActivityView view;

    @Inject
    public ActivityCategoriesPresenter(Activity activity) {
        this.view = (CategoriesActivityView) activity;
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
