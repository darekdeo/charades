package com.dariuszdeoniziak.charades.coordinators.categories;

import com.dariuszdeoniziak.charades.coordinators.categories.destinations.FormDestination;
import com.dariuszdeoniziak.charades.coordinators.categories.destinations.ListDestination;
import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.DestinationFactory;
import com.dariuszdeoniziak.charades.navigators.Navigator;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.utils.Logger;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.SingleSubject;

public class CategoriesCoordinatorImpl implements CategoriesCoordinator {

    private final SingleSubject<CategoriesResult> coordinatorResult = SingleSubject.create();
    private final Logger logger;
    private final DestinationCoordinatorStateMachine stateMachine;
    private final SchedulerFactory schedulerFactory;
    private final Navigator.Screen screenNavigator;
    private final ListDestination listDestination;
    private final FormDestination formDestination;

    @Inject
    public CategoriesCoordinatorImpl(
            Logger logger,
            DestinationCoordinatorStateMachine stateMachine,
            SchedulerFactory schedulerFactory,
            Navigator.Screen screenNavigator,
            DestinationFactory destinationFactory
    ) {
        this.logger = logger;
        this.stateMachine = stateMachine;
        this.schedulerFactory = schedulerFactory;
        this.screenNavigator = screenNavigator;
        listDestination = destinationFactory.create(ListDestination.class);
        listDestination.getPresenter().onTakeCoordination(this);
        formDestination = destinationFactory.create(FormDestination.class);
        formDestination.getPresenter().onTakeCoordination(this);
    }

    @Override
    public Single<CategoriesResult> coordinate() {
        stateMachine
                .state()
                .observeOn(schedulerFactory.ui())
                .subscribe(
                        state -> {
                            switch (state.state()) {
                                case NO_DESTINATION:
                                    navigateToDestination(listDestination);
                                case DISPLAYING_DESTINATION:
                                case NAVIGATING_TO_DESTINATION:
                                    state.getDestination().ifPresent(this::navigateToDestination);
                                case ERROR:
                                    break;
                            }
                        },
                        error -> logger.error("State error", error)
                );

        return coordinatorResult;
    }

    @Override
    public void selectCategory(Long categoryId) {

    }

    @Override
    public void editCategory(Long categoryId) {
        formDestination.getPresenter().onLoadCategory(categoryId);
        stateMachine.onNavigateToDestination(formDestination);
    }

    @Override
    public void addNewCategory() {
        formDestination.getPresenter().onNewCategory();
        stateMachine.onNavigateToDestination(formDestination);
    }

    @Override
    public void showConfirmDeleteCategory(Category category) {
        // TODO show confirmation screen/dialog before deleting
        listDestination.getPresenter().onDeleteCategory(category);
    }

    @Override
    public void closeCategoryForm() {
        stateMachine.onNavigateToDestination(listDestination);
    }

    private void navigateToDestination(Destination<?> destination) {
        screenNavigator
                .navigate(destination)
                .subscribe(
                        () -> stateMachine.onDestinationDisplayed(destination),
                        error -> {
                            logger.error("Navigator error", error);
                            stateMachine.onError(new Error(error));
                        }
                );
    }

}
