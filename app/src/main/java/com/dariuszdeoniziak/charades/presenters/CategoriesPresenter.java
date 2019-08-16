package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.datasources.PreferencesDataSource;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import javax.inject.Inject;


public class CategoriesPresenter extends AbstractPresenter<CategoriesView> {

    final PreferencesDataSource preferences;

    @Inject
    CategoriesPresenter(PreferencesDataSource preferences) {
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
