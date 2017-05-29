package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;

import javax.inject.Inject;

public class CategoriesFormPresenter implements Presenter<CategoriesFormView> {

    CategoriesFormView view;
    ModelInteractor modelInteractor;

    @Inject
    public CategoriesFormPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoriesFormView view) {
        this.view = view;
    }

    @Override
    public void onDropView() {
        view = null;
    }

    public void onTitleEdited(CharSequence title) {
        // todo save data
    }
}
