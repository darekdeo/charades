package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import static com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine.Transition;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.State;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.NavigateToDestination;
import com.dariuszdeoniziak.charades.utils.Optional;

public enum DestinationCoordinatorState implements State<Event<Transition, DestinationCoordinatorState>>, DestinationCoordinatorStateMachine.DataReader {

    NO_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorState onEvent(NavigateToDestination event) {
                    DestinationCoordinatorState state = DestinationCoordinatorState.NAVIGATING_TO_DESTINATION;
                    state.destination = Optional.of(event.destination);
                    return state;
                }

                @Override
                public DestinationCoordinatorState onEvent(DestinationDisplayed event) {
                    throw new IllegalStateException();
                }
            }
    ),
    NAVIGATING_TO_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorState onEvent(NavigateToDestination event) {
                    throw new IllegalStateException();
                }

                @Override
                public DestinationCoordinatorState onEvent(DestinationDisplayed event) {
                    DestinationCoordinatorState state = DestinationCoordinatorState.DISPLAYING_DESTINATION;
                    state.destination = Optional.of(event.destination);
                    return state;
                }
            }
    ),
    DISPLAYING_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorState onEvent(NavigateToDestination event) {
                    DestinationCoordinatorState state = DestinationCoordinatorState.NAVIGATING_TO_DESTINATION;
                    state.destination = Optional.of(event.destination);
                    return state;
                }

                @Override
                public DestinationCoordinatorState onEvent(DestinationDisplayed event) {
                    throw new IllegalStateException();
                }
            }
    );

    private final Transition transition;

    DestinationCoordinatorState(
            Transition transition
    ) {
        this.transition = transition;
    }

    private Optional<Destination<?>> destination = Optional.empty();

    @Override
    public DestinationCoordinatorState transition(Event<Transition, DestinationCoordinatorState> event) {
        return event.dispatch(transition);
    }

    @Override
    public Optional<Destination<?>> getDestination() {
        return destination;
    }
}
