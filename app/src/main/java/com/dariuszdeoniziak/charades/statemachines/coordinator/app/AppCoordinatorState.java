package com.dariuszdeoniziak.charades.statemachines.coordinator.app;

import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine.State;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.ScreenNavigatorAttached;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.TerminateApp;

public enum AppCoordinatorState implements State {

    STARTING(
            new AppCoordinatorStateMachine.Transition() {
                @Override
                public AppCoordinatorState onEvent(ScreenNavigatorAttached event) {
                    return AppCoordinatorState.STARTED;
                }

                @Override
                public AppCoordinatorState onEvent(TerminateApp event) {
                    return AppCoordinatorState.TERMINATED;
                }
            }
    ),

    STARTED(
            new AppCoordinatorStateMachine.Transition() {
                @Override
                public AppCoordinatorState onEvent(ScreenNavigatorAttached event) {
                    return null;
                }

                @Override
                public AppCoordinatorState onEvent(TerminateApp event) {
                    return AppCoordinatorState.TERMINATED;
                }
            }
    ),

    TERMINATED(
            new AppCoordinatorStateMachine.Transition() {
                @Override
                public AppCoordinatorState onEvent(ScreenNavigatorAttached event) {
                    return null;
                }

                @Override
                public AppCoordinatorState onEvent(TerminateApp event) {
                    return null;
                }
            }
    );

    private final AppCoordinatorStateMachine.Transition transition;

    AppCoordinatorState(AppCoordinatorStateMachine.Transition transition) {
        this.transition = transition;
    }

    @Override
    public AppCoordinatorState transition(AppCoordinatorStateMachine.Event event) {
        return event.dispatch(transition);
    }
}
