package com.dariuszdeoniziak.charades.coordinators.categories;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.navigators.Navigator;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.utils.Logger;
import com.dariuszdeoniziak.charades.utils.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.SingleSubject;

public class CategoriesCoordinatorImpl implements CategoriesCoordinator {

    private final SingleSubject<CategoriesResult> coordinatorResult = SingleSubject.create();
    private final Logger logger;
    private final DestinationCoordinatorStateMachine stateMachine;
    private final Navigator.Screen screenNavigator;

    @Inject
    public CategoriesCoordinatorImpl(
            Logger logger,
            DestinationCoordinatorStateMachine stateMachine,
            Navigator.Screen screenNavigator
    ) {
        this.logger = logger;
        this.stateMachine = stateMachine;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public Single<CategoriesResult> coordinate() {
        stateMachine
                .state()
                .subscribe(
                        state -> {
                            switch (state) {
                                case NO_DESTINATION:
                                    navigateToInitialDestination();
                                case DISPLAYING_DESTINATION:
                                case NAVIGATING_TO_DESTINATION:
                                    break;
                            }
                        },
                        error -> logger.error("State error", error)
                );

        return coordinatorResult;
    }

    private void navigateToInitialDestination() {
        navigateToDestination(Optional.of(CategoryDestination.LIST));
    }

    private void navigateToDestination(Optional<Destination> optionalDestination) {
        optionalDestination.ifPresent(destination -> screenNavigator
                .navigate(CategoryDestination.LIST)
                .subscribe(
                        () -> stateMachine.onDestinationDisplayed(destination),
                        error -> logger.error("Navigator error", error)
                ));
    }

}
