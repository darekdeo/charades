package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.AbsentView;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.google.common.base.Optional;

import javax.inject.Inject;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesFormPresenter implements Presenter<CategoriesFormView> {


    private Optional<CategoriesFormView> view = Optional.of(AbsentView.getInstance());
    private ModelInteractor modelInteractor;

    @Inject
    public CategoriesFormPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoriesFormView view) {
        this.view = Optional.fromNullable(view)
                .or(Optional.of(AbsentView.getInstance()));
    }

    @Override
    public void onDropView() {
        view = Optional.of(AbsentView.getInstance());
    }

    public void onTitleEdited(CharSequence title) {
        view.get().displayTextInfo("Title edited!");
        // todo save data
    }
}
