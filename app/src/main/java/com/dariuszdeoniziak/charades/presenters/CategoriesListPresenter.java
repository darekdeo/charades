package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.views.CategoriesListView;
import com.google.common.base.Optional;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings({"Guava", "OptionalUsedAsFieldOrParameterType"})
public class CategoriesListPresenter implements Presenter<CategoriesListView> {

    private Optional<CategoriesListView> view = Optional.absent();
    ModelInteractor modelInteractor;

    @Inject
    public CategoriesListPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoriesListView view) {
        this.view = Optional.fromNullable(view);
    }

    @Override
    public void onDropView() {
        view = Optional.absent();
    }

    public void loadCategories() {
        Single.fromCallable(() -> modelInteractor.getCategories())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(predicate -> view.isPresent())
                .doOnSubscribe(disposable -> view.get().showProgressIndicator())
                .doAfterTerminate(() -> view.get().hideProgressIndicator())
                .doOnSuccess(categories -> {
                    if (categories.isEmpty())
                        view.get().showEmptyList();
                    else
                        view.get().showCategories(categories);
                })
                .doOnError(throwable -> view.get().showEmptyList())
                .subscribe();
    }
}
