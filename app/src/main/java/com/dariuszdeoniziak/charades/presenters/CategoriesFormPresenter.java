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


public class CategoriesFormPresenter extends AbstractPresenter<CategoriesFormView> {

    private final ModelInteractor modelInteractor;
    private Optional<Disposable> saveCategoryDisposable = Optional.empty();

    public Category category = new Category();

    @Inject
    CategoriesFormPresenter(ModelInteractor modelInteractor) {
        this.modelInteractor = modelInteractor;
    }

    @Override
    public void onDropView() {
        saveCategoryDisposable.ifPresent(Disposable::dispose);
        super.onDropView();
    }

    public void loadCategory(long categoryId) {
        run(() -> Observable
                .fromCallable(() -> {
                    Optional.of(modelInteractor.getCategory(categoryId))
                            .ifPresent(category -> this.category = category);
                    return this.category;
                })
                .filter(category -> category.id != null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(category -> view.ifPresent(action -> action.showCategory(category))));
    }

    public void saveCategoryTitle(CharSequence title) {
        saveCategoryDisposable.ifPresent(Disposable::dispose);
        saveCategoryDisposable = Optional.of(Observable
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
