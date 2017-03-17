package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.activities.CategoriesActivity;

import javax.inject.Inject;

public class CategoriesActivityPresenter implements Presenter<CategoriesView> {

    CategoriesView view;
    PreferencesInteractor preferences;

    @Inject
    public CategoriesActivityPresenter(PreferencesInteractor preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.saveFirstRun();
    }

    @Override
    public void onTakeView(CategoriesView view) {
        this.view = view;
        if (!preferences.isFirstRun())
            view.displayTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        view.displayTextInfo("View is dying!");
        view = null;
    }
}
