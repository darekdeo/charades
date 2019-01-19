package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesView;

import javax.inject.Inject;


public class CategoriesActivityPresenter implements Presenter<CategoriesView> {

    private Optional<CategoriesView> view = Optional.empty();
    PreferencesInteractor preferences;

    @Inject
    CategoriesActivityPresenter(PreferencesInteractor preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.saveFirstRun();
    }

    @Override
    public void onTakeView(CategoriesView view) {
        this.view = Optional.of(view);
        if (!preferences.isFirstRun())
            view.showTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        view.ifPresent(action -> action.showTextInfo("View is dying!"));
        view = Optional.empty();
    }
}
