package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorState;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine;

public final class DestinationDisplayed implements Event<DestinationCoordinatorStateMachine.Transition, DestinationCoordinatorState> {
    public final Destination destination;

    public DestinationDisplayed(Destination destination) {
        this.destination = destination;
    }

    @Override
    public DestinationCoordinatorState dispatch(DestinationCoordinatorStateMachine.Transition transition) {
        return transition.onEvent(this);
    }

}
