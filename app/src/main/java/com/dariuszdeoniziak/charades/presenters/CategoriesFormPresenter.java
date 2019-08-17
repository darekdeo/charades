package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesFormView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CategoriesFormPresenter extends AbstractPresenter<CategoriesFormView> {

    private final CharadesRepository charadesRepository;
    private Optional<Disposable> loadCategoryDisposable = Optional.empty();
    private Optional<Disposable> saveCategoryDisposable = Optional.empty();

    public Category category = new Category();

    @Inject
    CategoriesFormPresenter(CharadesRepository charadesRepository) {
        this.charadesRepository = charadesRepository;
    }

    @Override
    public void onDropView() {
        saveCategoryDisposable.ifPresent(Disposable::dispose);
        super.onDropView();
    }

    public void loadCategory(Long categoryId) {
        if (categoryId > 0L) {
            loadCategoryDisposable.ifPresent(Disposable::dispose);
            loadCategoryDisposable = Optional.of(charadesRepository.getCategory(categoryId)
                    .doOnSuccess((category) -> this.category = category)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(category -> view.ifPresent(action -> action.showCategory(category))));
        }
    }

    public void saveCategoryTitle(CharSequence title) {
        saveCategoryDisposable.ifPresent(Disposable::dispose);
        saveCategoryDisposable = Optional.of(Single.just(category)
                .map((category) -> {
                    category.name = title.toString();
                    return category;
                })
                .flatMap(charadesRepository::saveCategory)
                .doOnSuccess((categoryId) -> category.id = categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(categoryId -> view.ifPresent(action -> action.showTextInfo(
                        "Title edited for category: " + categoryId))));
    }
}
