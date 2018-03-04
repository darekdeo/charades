package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.AbsentView;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;
import com.google.common.base.Optional;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesFormPresenter implements Presenter<CategoriesFormView> {


    private Optional<CategoriesFormView> view = Optional.of(AbsentView.getInstance());
    private ModelInteractor modelInteractor;

    public Category category = new Category();

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

    public void loadCategory(int categoryId) {
        // TODO: load from database and present on view
    }

    public void onTitleEdited(CharSequence title) {
        Observable
                .fromCallable(() -> {
                    category.setName(title.toString());
                    return modelInteractor.saveCategory(category);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(categoryId -> view.get().displayTextInfo(
                        "Title edited for category: " + categoryId));
    }
}
