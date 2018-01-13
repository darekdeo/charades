package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.google.common.base.Optional;

import javax.inject.Inject;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesActivityPresenter implements Presenter<CategoriesView> {

    Optional<CategoriesView> view = Optional.absent();
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
        this.view = Optional.fromNullable(view);
        if (!preferences.isFirstRun())
            view.displayTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        if (view.isPresent())
            view.get().displayTextInfo("View is dying!");
        view = Optional.absent();
    }
}
