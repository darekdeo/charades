package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CategoriesListPresenter implements Presenter<CategoriesListView> {

    final ModelInteractor modelInteractor;
    private Optional<CategoriesListView> view = Optional.empty();

    @Inject
    CategoriesListPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onTakeView(CategoriesListView view) {
        this.view = Optional.of(view);
    }

    @Override
    public void onDropView() {
        view = Optional.empty();
    }

    void loadCategories() {
        Single.fromCallable(modelInteractor::getCategories)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.ifPresent(CategoriesListView::showProgressIndicator))
                .doOnSuccess(categories -> view.ifPresent(action -> {
                    if (categories.isEmpty())
                        action.showEmptyList();
                    else
                        action.showCategories(categories);
                }))
                .doOnError(throwable -> view.ifPresent(CategoriesListView::showEmptyList))
                .doFinally(() -> view.ifPresent(CategoriesListView::hideProgressIndicator))
                .subscribe();
    }
}
