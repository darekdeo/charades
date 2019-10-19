package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CategoriesListPresenter extends AbstractPresenter<CategoriesListContract.CategoriesListView>
        implements CategoriesListContract.ListItemPresenter {

    final CharadesRepository charadesRepository;

    @Inject
    CategoriesListPresenter(CharadesRepository charadesRepository) {
        this.charadesRepository = charadesRepository;
    }

    public void onLoadCategories() {
        run(() -> charadesRepository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.ifPresent(CategoriesListContract.CategoriesListView::showProgressIndicator))
                .doOnSuccess(categories -> view.ifPresent(action -> {
                    if (categories.isEmpty())
                        action.showEmptyList();
                    else
                        action.showCategories(categories);
                }))
                .doOnError(throwable -> view.ifPresent(CategoriesListContract.CategoriesListView::showEmptyList))
                .doFinally(() -> view.ifPresent(CategoriesListContract.CategoriesListView::hideProgressIndicator))
                .subscribe());
    }

    public void onConfirmDeleteCategory(Category category) {
        run(() -> charadesRepository.deleteCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.ifPresent(CategoriesListContract.CategoriesListView::showProgressIndicator))
                .flatMap(s -> charadesRepository.getCategories())
                .doOnSuccess(categories -> view.ifPresent(action -> {
                    if (categories.isEmpty())
                        action.showEmptyList();
                    else
                        action.showCategories(categories);
                }))
                .doOnError(throwable -> view.ifPresent(CategoriesListContract.CategoriesListView::showEmptyList))
                .doFinally(() -> view.ifPresent(CategoriesListContract.CategoriesListView::hideProgressIndicator))
                .subscribe());
    }

    @Override
    public void onSelect(Category category) {
        view.ifPresent((action) -> action.selectCategory(category.id));
    }

    @Override
    public void onEdit(Category category) {
        view.ifPresent((action) -> action.editCategory(category.id));
    }

    @Override
    public void onDelete(Category category) {
        view.ifPresent((action) -> action.showConfirmDeleteCategory(category));
    }
}
