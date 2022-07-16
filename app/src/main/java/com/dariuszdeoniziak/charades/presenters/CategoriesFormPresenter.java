package com.dariuszdeoniziak.charades.presenters;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.Label;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.utils.Mapper;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.utils.Pair;
import com.dariuszdeoniziak.charades.views.CategoriesFormContract;
import com.dariuszdeoniziak.charades.views.models.CategoriesFormModel;
import com.dariuszdeoniziak.charades.views.models.CharadeListItemModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;


public class CategoriesFormPresenter extends AbstractPresenter<CategoriesFormContract.View>
        implements
        CategoriesFormContract.Presenter,
        CategoriesFormContract.CharadeListItemPresenter {

    private final LabelsRepository labelsRepository;
    private final CharadesRepository charadesRepository;
    private final SchedulerFactory schedulerFactory;
    private final Mapper<Charade, CharadeListItemModel> toCharadeListItemModelMapper;

    private Disposable loadCategoryDisposable = Disposable.empty();
    private Disposable saveCategoryDisposable = Disposable.empty();

    private Optional<CategoriesFormContract.Coordination> coordination = Optional.empty();

    public Category category = new Category();

    @Inject
    CategoriesFormPresenter(
            LabelsRepository labelsRepository,
            CharadesRepository charadesRepository,
            SchedulerFactory schedulerFactory,
            @Named("to_charade_list_item_model_mapper") Mapper<Charade, CharadeListItemModel> toCharadeListItemModelMapper
    ) {
        this.labelsRepository = labelsRepository;
        this.charadesRepository = charadesRepository;
        this.schedulerFactory = schedulerFactory;
        this.toCharadeListItemModelMapper = toCharadeListItemModelMapper;
    }

    @Override
    public void onTakeView(CategoriesFormContract.View view) {
        super.onTakeView(view);
        CategoriesFormModel model = new CategoriesFormModel();
        model.title = labelsRepository.getLabel(Label.category_form_header);
        view.setup(model);
    }

    @Override
    public void onDropView() {
        loadCategoryDisposable.dispose();
        saveCategoryDisposable.dispose();
        super.onDropView();
    }

    @Override
    public void onTakeCoordination(CategoriesFormContract.Coordination coordination) {
        this.coordination = Optional.of(coordination);
    }

    @Override
    public void onLoadCategory(Long categoryId) {
        if (categoryId > 0L) {
            loadCategoryDisposable.dispose();
            loadCategoryDisposable = Single.zip(
                    charadesRepository.getCategory(categoryId),
                    charadesRepository.getCharades(categoryId)
                            .toObservable()
                            .flatMapIterable(list -> list)
                            .map(toCharadeListItemModelMapper::map)
                            .toList(),
                    Pair::new)
                    .doOnSuccess((pair) -> this.category = pair.getFirst())
                    .observeOn(schedulerFactory.ui())
                    .subscribeOn(schedulerFactory.io())
                    .subscribe(category -> view.ifPresent(action -> {
                        action.showCategory(category.getFirst());
                        action.showCharades(category.getSecond());
                    }));
        }
    }

    @Override
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
    public void onClose() {
        coordination.ifPresent(CategoriesFormContract.Coordination::closeCategoryForm);
    }

    @Override
    public void onEdited(CharadeListItemModel charade, String editedText) {

    }

    @Override
    public void onDelete(CharadeListItemModel charade) {

    }

    @Override
    public void onNew() {

    }

}
