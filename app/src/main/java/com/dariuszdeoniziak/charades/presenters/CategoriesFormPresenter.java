package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.AbsentView;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.google.common.base.Optional;

import javax.inject.Inject;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesFormPresenter implements Presenter<CategoriesFormView> {


    private Optional<CategoriesFormView> view = AbsentView.getOptional();
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
        this.view = Optional.of(view);
    }

    @Override
    public void onDropView() {
        view = AbsentView.getOptional();
    }

    public void onTitleEdited(CharSequence title) {
        view.get().displayTextInfo("Title edited!");
        // todo save data
    }
}
