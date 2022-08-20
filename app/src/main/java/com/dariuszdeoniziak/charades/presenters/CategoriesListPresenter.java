package com.dariuszdeoniziak.charades.presenters;

import static com.dariuszdeoniziak.charades.views.CategoriesListContract.View;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.data.repositories.CharadesRepository;
import com.dariuszdeoniziak.charades.data.repositories.LabelsRepository;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.CategoriesListStateMachine;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.utils.Optional;
import com.dariuszdeoniziak.charades.views.CategoriesListContract;

import java.util.Collections;

import javax.inject.Inject;

public class CategoriesListPresenter extends AbstractPresenter<View>
        implements
        CategoriesListContract.Presenter,
        CategoriesListContract.ListItemPresenter {

    private final CharadesRepository charadesRepository;
    private final LabelsRepository labelsRepository;
    private final SchedulerFactory schedulerFactory;
    private final CategoriesListStateMachine stateMachine;
    private final Logger logger;

    private Optional<CategoriesListContract.Coordination> coordination = Optional.empty();

    @Inject
    CategoriesListPresenter(
            CharadesRepository charadesRepository,
            LabelsRepository labelsRepository,
            SchedulerFactory schedulerFactory,
            CategoriesListStateMachine stateMachine,
            Logger logger
    ) {
        this.charadesRepository = charadesRepository;
        this.labelsRepository = labelsRepository;
        this.schedulerFactory = schedulerFactory;
        this.stateMachine = stateMachine;
        this.logger = logger;
    }

    @Override
    public void onTakeView(CategoriesListContract.View view) {
        super.onTakeView(view);
        sideEffects();
    }

    private void sideEffects() {
        run(() -> stateMachine.state()
                .observeOn(schedulerFactory.ui())
                .subscribe(
                        state -> {
                            switch (state) {
                                case EMPTY_LIST:
                                case ERROR:
                                    view.ifPresent(action -> {
                                        action.hideProgressIndicator();
                                        action.showCategories(Collections.emptyList());
                                        action.showEmptyList();
                                    });
                                    break;
                                case LIST_WITH_ITEMS:
                                    view.ifPresent(action -> {
                                        action.hideProgressIndicator();
                                        action.showCategories(state.getCategories());
                                    });
                                    break;
                                case DELETING:
                                    view.ifPresent(View::showProgressIndicator);

                                    run(() -> charadesRepository.deleteCategory(state.getDeletingCategory())
                                            .subscribeOn(schedulerFactory.io())
                                            .observeOn(schedulerFactory.ui())
                                            .subscribe(
                                                    (id) -> stateMachine.onLoadList(),
                                                    stateMachine::onError
                                            ));
                                    break;
                                case LOADING:
                                    view.ifPresent(View::showProgressIndicator);

                                    run(() -> charadesRepository.getCategories()
                                            .subscribeOn(schedulerFactory.io())
                                            .observeOn(schedulerFactory.ui())
                                            .subscribe(
                                                    stateMachine::onListLoaded,
                                                    stateMachine::onError
                                            ));
                                    break;
                            }
                        },
                        error -> logger.error("State error", error)
                )
        );
    }

    @Override
    public void onTakeCoordination(CategoriesListContract.Coordination coordination) {
        this.coordination = Optional.of(coordination);
    }

    @Override
    public void onLoadCategories() {
        stateMachine.onLoadList();
    }

    @Override
    public void onDeleteCategory(Category category) {
        stateMachine.onDelete(category);
    }

    @Override
    public void onAddNew() {
        coordination.ifPresent(CategoriesListContract.Coordination::addNewCategory);
    }

    @Override
    public void onSelect(Category category) {
        coordination.ifPresent((action) -> action.selectCategory(category.id));
    }

    @Override
    public void onEdit(Category category) {
        coordination.ifPresent((action) -> action.editCategory(category.id));
    }

    @Override
    public void onDelete(Category category) {
        coordination.ifPresent((action) -> action.showConfirmDeleteCategory(category));
    }
}
