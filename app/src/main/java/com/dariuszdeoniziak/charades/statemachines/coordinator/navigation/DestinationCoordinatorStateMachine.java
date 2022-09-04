package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.Error;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.NavigateToDestination;
import com.dariuszdeoniziak.charades.utils.Optional;

import io.reactivex.rxjava3.core.Observable;

public interface DestinationCoordinatorStateMachine {

    // STATE
    Observable<ResultState> state();
    interface State extends com.dariuszdeoniziak.charades.statemachines.State<DestinationCoordinatorStateMachine.Event, DestinationCoordinatorStateMachine.ResultState> {}
    interface ResultState {
        DestinationCoordinatorState state();
        Optional<Destination<?>> getDestination();
    }

    // EVENTS
    interface Event extends com.dariuszdeoniziak.charades.statemachines.Event<DestinationCoordinatorStateMachine.Transition, DestinationCoordinatorStateMachine.ResultState> {}
    void onNavigateToDestination(Destination<?> destination);
    void onDestinationDisplayed(Destination<?> destination);
    void onError(Throwable error);

    // TRANSITION
    interface Transition {
        default ResultState onEvent(NavigateToDestination event) {
            return DestinationCoordinatorState.invalid();
        };
        default ResultState onEvent(DestinationDisplayed event) {
            return DestinationCoordinatorState.invalid();
        };
        default ResultState onEvent(Error event) {
            return DestinationCoordinatorState.invalid();
        };
    }
}
