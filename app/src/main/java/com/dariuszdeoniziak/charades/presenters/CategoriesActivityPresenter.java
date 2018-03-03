package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.PreferencesInteractor;
import com.dariuszdeoniziak.charades.views.AbsentView;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.google.common.base.Optional;

import javax.inject.Inject;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesActivityPresenter implements Presenter<CategoriesView> {

    private Optional<CategoriesView> view = Optional.of(AbsentView.getInstance());
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
        this.view = Optional.fromNullable(view)
                .or(Optional.of(AbsentView.getInstance()));
        if (!preferences.isFirstRun())
            view.displayTextInfo("Hello again!");
    }

    @Override
    public void onDropView() {
        view.get().displayTextInfo("View is dying!");
        view = Optional.of(AbsentView.getInstance());
    }
}
