package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.datasources.CharadesDataSource;
import com.dariuszdeoniziak.charades.views.CategoriesListView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CategoriesListPresenter extends AbstractPresenter<CategoriesListView> {

    final CharadesDataSource charadesDataSource;

    @Inject
    CategoriesListPresenter(CharadesDataSource charadesDataSource) {
        this.charadesDataSource = charadesDataSource;
    }

    void loadCategories() {
        run(() -> Single.fromCallable(charadesDataSource::getCategories)
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
                .subscribe());
    }
}
