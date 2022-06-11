package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.NavigateToDestination;
import com.dariuszdeoniziak.charades.utils.Optional;

import io.reactivex.rxjava3.core.Observable;

public interface DestinationCoordinatorStateMachine {

    // STATE
    Observable<DestinationCoordinatorState> state();
    interface DataReader {
        Optional<Destination> getDestination();
    }

    // EVENTS
    void onNavigateToDestination(Destination destination);
    void onDestinationDisplayed(Destination destination);

    // TRANSITION
    interface Transition {
        DestinationCoordinatorState onEvent(NavigateToDestination event);
        DestinationCoordinatorState onEvent(DestinationDisplayed event);
    }
}
