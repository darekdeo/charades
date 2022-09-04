package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events;

import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;

public final class Error implements DestinationCoordinatorStateMachine.Event {
    final Throwable error;

    public Error(Throwable error) {
        this.error = error;
    }

    @Override
    public DestinationCoordinatorStateMachine.ResultState dispatch(DestinationCoordinatorStateMachine.Transition transition) {
        return transition.onEvent(this);
    }
}
