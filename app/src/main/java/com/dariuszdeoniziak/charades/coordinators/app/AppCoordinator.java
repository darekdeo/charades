package com.dariuszdeoniziak.charades.coordinators.app;

import com.dariuszdeoniziak.charades.coordinators.Coordinator;
import com.dariuszdeoniziak.charades.coordinators.categories.CategoriesResult;
import com.dariuszdeoniziak.charades.navigators.ScreenNavigatorHost;
import com.dariuszdeoniziak.charades.schedulers.SchedulerFactory;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;
import com.dariuszdeoniziak.charades.utils.Logger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.SingleSubject;

public class AppCoordinator implements Coordinator<AppResult> {

    private final SingleSubject<AppResult> coordinatorResult = SingleSubject.create();
    private final Logger logger;
    private final AppCoordinatorStateMachine stateMachine;
    private final SchedulerFactory schedulerFactory;
    private final ScreenNavigatorHost.Monitor screenNavigatorHostMonitor;
    private final Coordinator<CategoriesResult> categoriesCoordinator;

    public AppCoordinator(
            Logger logger,
            AppCoordinatorStateMachine stateMachine,
            SchedulerFactory schedulerFactory,
            ScreenNavigatorHost.Monitor screenNavigatorHostMonitor,
            Coordinator<CategoriesResult> categoriesCoordinator
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
                                case TERMINATED:
                                    coordinatorResult.onSuccess(AppResult.TERMINATED_APP);
                                    break;
                                case STARTED:
                                    coordinateToFirstScreen();
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

    private void coordinateToFirstScreen() {
        categoriesCoordinator.coordinate()
                .subscribe(
                        categoriesResult -> {
                            logger.info("Finished app coordinator");
                        },
                        throwable -> logger.error("App coordinator error", throwable)
                );
    }

}
