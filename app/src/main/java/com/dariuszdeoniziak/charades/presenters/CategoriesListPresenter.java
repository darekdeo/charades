package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Label;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CategoriesListPresenter extends AbstractPresenter<CategoriesListContract.View>
        implements CategoriesListContract.ListItemPresenter {

    private final CharadesRepository charadesRepository;
    private final LabelsRepository labelsRepository;

    @Inject
    CategoriesListPresenter(CharadesRepository charadesRepository, LabelsRepository labelsRepository) {
        this.charadesRepository = charadesRepository;
        this.labelsRepository = labelsRepository;
    }

    public void onLoadCategories() {
        run(() -> charadesRepository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.ifPresent(CategoriesListContract.View::showProgressIndicator))
                .doOnSuccess(categories -> view.ifPresent(action -> {
                    if (categories.isEmpty())
                        action.showEmptyList();
                    else
                        action.showCategories(categories);
                }))
                .doOnError(throwable -> view.ifPresent(CategoriesListContract.View::showEmptyList))
                .doFinally(() -> view.ifPresent(CategoriesListContract.View::hideProgressIndicator))
                .subscribe());
    }

    public void onConfirmDeleteCategory(Category category) {
        run(() -> charadesRepository.deleteCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.ifPresent(CategoriesListContract.View::showProgressIndicator))
                .flatMap(s -> charadesRepository.getCategories())
                .doOnSuccess(categories -> view.ifPresent(action -> {
                    if (categories.isEmpty())
                        action.showEmptyList();
                    else
                        action.showCategories(categories);
                }))
                .doOnError(throwable -> view.ifPresent(CategoriesListContract.View::showEmptyList))
                .doFinally(() -> view.ifPresent(CategoriesListContract.View::hideProgressIndicator))
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
        view.ifPresent((action) -> action.showConfirmDeleteCategory(
                category,
                labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_title),
                labelsRepository.getLabel(Label.categories_list_dialog_confirm_delete_message, category.name),
                labelsRepository.getLabel(Label.yes),
                labelsRepository.getLabel(Label.no)
        ));
    }
}
