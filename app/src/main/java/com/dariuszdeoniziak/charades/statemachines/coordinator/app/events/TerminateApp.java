package com.dariuszdeoniziak.charades.statemachines.coordinator.app.events;

import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorState;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.AppCoordinatorStateMachine;

public final class TerminateApp implements Event<AppCoordinatorStateMachine.Transition, AppCoordinatorState> {

    @Override
    public AppCoordinatorState dispatch(AppCoordinatorStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
