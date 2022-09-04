package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import static com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.DestinationCoordinatorStateMachine.Transition;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.NavigateToDestination;
import com.dariuszdeoniziak.charades.utils.Optional;

public enum DestinationCoordinatorState implements DestinationCoordinatorStateMachine.State {

    NO_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(NavigateToDestination event) {
                    return valid(DestinationCoordinatorState.NAVIGATING_TO_DESTINATION, Optional.of(event.destination));
                }

                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(DestinationDisplayed event) {
                    return invalid();
                }
            }
    ),
    NAVIGATING_TO_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(NavigateToDestination event) {
                    return invalid();
                }

                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(DestinationDisplayed event) {
                    return valid(DestinationCoordinatorState.DISPLAYING_DESTINATION, Optional.of(event.destination));
                }
            }
    ),
    DISPLAYING_DESTINATION(
            new Transition() {
                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(NavigateToDestination event) {
                    return valid(DestinationCoordinatorState.NAVIGATING_TO_DESTINATION, Optional.of(event.destination));
                }

                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(DestinationDisplayed event) {
                    return invalid();
                }
            }
    ),
    ERROR(
            new Transition() {
                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(NavigateToDestination event) {
                    return valid(DestinationCoordinatorState.NAVIGATING_TO_DESTINATION, Optional.of(event.destination));
                }

                @Override
                public DestinationCoordinatorStateMachine.ResultState onEvent(DestinationDisplayed event) {
                    return invalid();
                }
            }
    );

    private final Transition transition;

    static DestinationCoordinatorStateMachine.ResultState valid(DestinationCoordinatorState state, Optional<Destination<?>> destination) {
        return new DestinationCoordinatorStateMachine.ResultState() {
            @Override
            public DestinationCoordinatorState state() {
                return state;
            }

            @Override
            public Optional<Destination<?>> getDestination() {
                return destination;
            }
        };
    }

    static DestinationCoordinatorStateMachine.ResultState invalid() {
        return new DestinationCoordinatorStateMachine.ResultState() {
            @Override
            public DestinationCoordinatorState state() {
                return ERROR;
            }

            @Override
            public Optional<Destination<?>> getDestination() {
                return Optional.empty();
            }
        };
    }

    static DestinationCoordinatorStateMachine.ResultState initial() {
        return new DestinationCoordinatorStateMachine.ResultState() {
            @Override
            public DestinationCoordinatorState state() {
                return NO_DESTINATION;
            }

            @Override
            public Optional<Destination<?>> getDestination() {
                return Optional.empty();
            }
        };
    }

    DestinationCoordinatorState(
            Transition transition
    ) {
        this.transition = transition;
    }

    @Override
    public DestinationCoordinatorStateMachine.ResultState transition(DestinationCoordinatorStateMachine.Event event) {
        return event.dispatch(transition);
    }
}
