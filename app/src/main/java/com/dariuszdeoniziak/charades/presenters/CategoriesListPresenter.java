package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.views.CategoriesListView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CategoriesListPresenter extends AbstractPresenter<CategoriesListView> {

    final CharadesRepository charadesRepository;

    @Inject
    CategoriesListPresenter(CharadesRepository charadesRepository) {
        this.charadesRepository = charadesRepository;
    }

    public void onLoadCategories() {
        run(() -> charadesRepository.getCategories()
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

    public void onSelectCategory(Category category) {
        // TODO implement on select category, pass action to activity
    }

    public void onEditCategory(Category category) {
        view.ifPresent((action) -> action.editCategory(category.id));
    }

    public void onDeleteCategory(Category category) {
        // TODO implement on delete category, pass action to activity
    }
}
