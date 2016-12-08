package com.dariuszdeoniziak.charades.activities.presenters;

import com.dariuszdeoniziak.charades.activities.views.CategoriesActivityView;

import javax.inject.Inject;

/**
 * Created by darek on 08.12.16.
 */

public class ActivityCategoriesPresenter extends BasePresenter {

    private CategoriesActivityView view;

    @Inject
    public ActivityCategoriesPresenter(CategoriesActivityView view) {
        this.view = view;
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
