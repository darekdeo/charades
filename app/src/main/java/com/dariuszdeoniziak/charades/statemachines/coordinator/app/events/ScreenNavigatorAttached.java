package com.dariuszdeoniziak.charades.statemachines.coordinator.app.events;

import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorState;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;

public final class ScreenNavigatorAttached implements AppCoordinatorStateMachine.Event {

    @Override
    public AppCoordinatorState dispatch(AppCoordinatorStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
