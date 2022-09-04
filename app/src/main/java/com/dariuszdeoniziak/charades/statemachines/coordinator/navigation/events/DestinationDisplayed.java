package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;

public final class DestinationDisplayed implements DestinationCoordinatorStateMachine.Event {
    public final Destination<?> destination;

    public DestinationDisplayed(Destination<?> destination) {
        this.destination = destination;
    }

    @Override
    public DestinationCoordinatorStateMachine.ResultState dispatch(DestinationCoordinatorStateMachine.Transition transition) {
        return transition.onEvent(this);
    }

}
