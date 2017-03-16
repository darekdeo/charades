package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import javax.inject.Inject;

public class CategoriesActivityPresenter extends BasePresenter {

    private CategoriesView view;
    private PreferencesInteractor preferences;

    @Inject
    public CategoriesActivityPresenter(CategoriesView activity, PreferencesInteractor preferences) {
        this.view = activity;
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.saveFirstRun();
    }

    @Override
    public void onTakeView() {
        if (preferences.isFirstRun())
            view.displayTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        view.displayTextInfo("View is dying!");
        view = null;
    }
}
