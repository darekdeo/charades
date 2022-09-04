package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
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

    // TRANSITION
    interface Transition {
        ResultState onEvent(NavigateToDestination event);
        ResultState onEvent(DestinationDisplayed event);
    }
}
