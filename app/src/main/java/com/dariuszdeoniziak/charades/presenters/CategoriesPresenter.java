package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.repositories.PreferencesRepository;
import com.dariuszdeoniziak.charades.views.CategoriesView;
import com.dariuszdeoniziak.charades.views.CategoryScreen;

import javax.inject.Inject;


public class CategoriesPresenter extends AbstractPresenter<CategoriesView> {

    private final PreferencesRepository preferences;

    @Inject
    CategoriesPresenter(PreferencesRepository preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onSave() {
        preferences.saveFirstRun();
    }

    @Override
    public void onTakeView(CategoriesView view) {
        super.onTakeView(view);
        if (!preferences.isFirstRun().blockingGet())
            view.showTextInfo("Hello again!");
        if (view.getCurrentScreen() == CategoryScreen.NONE)
            view.toList();
    }

    @Override
    public void onDropView() {
        view.ifPresent(action -> action.showTextInfo("View is dying!"));
        super.onDropView();
    }

    public void onToggleViewMode() {
        view.ifPresent((action) -> {
            CategoryScreen screen = action.getCurrentScreen();
            switch (screen) {
                case LIST:
                    view.ifPresent(CategoriesView::toForm);
                    break;
                case FORM:
                    view.ifPresent(CategoriesView::toList);
                    break;
            }
        });
    }
}
