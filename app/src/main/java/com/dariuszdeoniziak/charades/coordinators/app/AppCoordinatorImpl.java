package com.dariuszdeoniziak.charades.coordinators.app;

import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesCoordinator;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHostMonitor;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.utils.Logger;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.SingleSubject;

public class AppCoordinatorImpl implements AppCoordinator {

    private final SingleSubject<AppResult> coordinatorResult = SingleSubject.create();
    private final Logger logger;
    private final AppCoordinatorStateMachine stateMachine;
    private final SchedulerFactory schedulerFactory;
    private final ScreenNavigatorHostMonitor screenNavigatorHostMonitor;
    private final CategoriesCoordinator categoriesCoordinator;

    @Inject
    public AppCoordinatorImpl(
            Logger logger,
            AppCoordinatorStateMachine stateMachine,
            SchedulerFactory schedulerFactory,
            ScreenNavigatorHostMonitor screenNavigatorHostMonitor,
            CategoriesCoordinator categoriesCoordinator
    ) {
        this.logger = logger;
        this.stateMachine = stateMachine;
        this.schedulerFactory = schedulerFactory;
        this.screenNavigatorHostMonitor = screenNavigatorHostMonitor;
        this.categoriesCoordinator = categoriesCoordinator;
    }

    @Override
    public Single<AppResult> coordinate() {
        stateMachine.state()
                .observeOn(schedulerFactory.ui())
                .subscribe(
                        state -> {
                            switch (state) {
                                case STARTING:
                                    startScreenNavigatorHostMonitor();
                                    break;
                                case TERMINATED:
                                    coordinatorResult.onSuccess(AppResult.TERMINATED_APP);
                                    break;
                                case STARTED:
                                    coordinateToCategories();
                                    break;
                            }
                        },
                        error -> logger.error("State error", error)
                );

        return coordinatorResult;
    }

    private void startScreenNavigatorHostMonitor() {
        screenNavigatorHostMonitor
                .getStatus()
                .subscribe(
                        hostStatus -> {
                            logger.info("Host status changed: " + hostStatus);
                            switch (hostStatus) {
                                case ATTACHED:
                                    stateMachine.onScreenHostAttached();
                                    break;
                                case DETACHED:
                                    break;
                            }
                        },
                        throwable -> logger.error("App coordinator error", throwable)
                );
    }

    private void coordinateToCategories() {
        categoriesCoordinator.coordinate()
                .subscribe(
                        categoriesResult -> {
                            logger.info("Finished app coordinator");
                        },
                        throwable -> logger.error("App coordinator error", throwable)
                );
    }

}
