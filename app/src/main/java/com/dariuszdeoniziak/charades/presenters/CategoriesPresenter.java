package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import javax.inject.Inject;


public class CategoriesPresenter extends AbstractPresenter<CategoriesView> {

    final PreferencesInteractor preferences;

    @Inject
    CategoriesPresenter(PreferencesInteractor preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.saveFirstRun();
    }

    @Override
    public void onTakeView(CategoriesView view) {
        super.onTakeView(view);
        if (!preferences.isFirstRun())
            view.showTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        view.ifPresent(action -> action.showTextInfo("View is dying!"));
        super.onDropView();
    }
}
