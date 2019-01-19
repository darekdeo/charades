package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.models.Category;
import com.dariuszdeoniziak.charades.models.interactors.ModelInteractor;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CategoriesFormPresenter implements Presenter<CategoriesFormView> {

    private Optional<CategoriesFormView> view = Optional.empty();
    private ModelInteractor modelInteractor;
    private Optional<Disposable> disposable = Optional.empty();

    public Category category = new Category();

    @Inject
    CategoriesFormPresenter(ModelInteractor modelInteractor) {
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
        disposable.ifPresent(Disposable::dispose); // TODO write tests
        disposable.ifPresent(Disposable::dispose);
        view = Optional.empty();
    }

    public void loadCategory(long categoryId) {
        disposable = Optional.of(Observable
                .fromCallable(() -> {
                    Category category = modelInteractor.getCategory(categoryId);
                    if (category != null)
                        this.category = category;
                    return this.category;
                })
                .filter(category -> category.id != null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(category -> view.ifPresent(action -> action.showCategory(category))));
    }

    public void saveCategoryTitle(CharSequence title) {
        disposable.ifPresent(Disposable::dispose); // TODO write tests
        disposable = Optional.of(Observable
                .fromCallable(() -> {
                    category.name = title.toString();
                    return modelInteractor.saveCategory(category);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(categoryId -> view.ifPresent(action -> action.showTextInfo(
                        "Title edited for category: " + categoryId))));
    }
}
