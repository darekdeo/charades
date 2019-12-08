package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.subjects.PublishSubject;


public class CategoriesFormPresenter extends AbstractPresenter<CategoriesFormContract.View> implements CategoriesFormContract.ListItemPresenter {

    static final int TYPING_DELAY = 1;

    private final CharadesRepository charadesRepository;
    private final SchedulerFactory schedulerFactory;
    private PublishSubject<String> titleEditedSubject = PublishSubject.create();
    private final Disposable titleEditedDisposable;
    private Disposable loadCategoryDisposable = Disposables.empty();
    private Disposable saveCategoryDisposable = Disposables.empty();

    public Category category = new Category();

    @Inject
    CategoriesFormPresenter(CharadesRepository charadesRepository, SchedulerFactory schedulerFactory) {
        this.charadesRepository = charadesRepository;
        this.schedulerFactory = schedulerFactory;

        titleEditedDisposable = titleEditedSubject
                .debounce(TYPING_DELAY, TimeUnit.SECONDS, schedulerFactory.computation())
                .filter(charSequence -> charSequence.length() > 0)
                .map(CharSequence::toString)
                .observeOn(schedulerFactory.ui())
                .subscribe(this::onSaveCategoryTitle);
    }

    @Override
    public void onDropView() {
        titleEditedDisposable.dispose();
        loadCategoryDisposable.dispose();
        saveCategoryDisposable.dispose();
        super.onDropView();
    }

    public void onLoadCategory(Long categoryId) {
        if (categoryId > 0L) {
            loadCategoryDisposable.dispose();
            loadCategoryDisposable = charadesRepository.getCategory(categoryId)
                    .doOnSuccess((category) -> this.category = category)
                    .observeOn(schedulerFactory.ui())
                    .subscribeOn(schedulerFactory.io())
                    .subscribe(category -> view.ifPresent(action -> action.showCategory(category)));
        }
    }

    public void onEditedCategoryTitle(String title) {
        titleEditedSubject.onNext(title);
    }

    public void onSaveCategoryTitle(String title) {
        saveCategoryDisposable.dispose();
        saveCategoryDisposable = Single.just(category)
                .map((category) -> {
                    category.name = title;
                    return category;
                })
                .flatMap(charadesRepository::saveCategory)
                .doOnSuccess((categoryId) -> category.id = categoryId)
                .observeOn(schedulerFactory.ui())
                .subscribeOn(schedulerFactory.io())
                .subscribe(categoryId -> view.ifPresent(action -> action.showTextInfo(
                        "Title edited for category: " + categoryId)));
    }

    @Override
    public void onEdited(Charade category, String editedText) {

    }

    @Override
    public void onDelete(Charade category) {

    }
}
