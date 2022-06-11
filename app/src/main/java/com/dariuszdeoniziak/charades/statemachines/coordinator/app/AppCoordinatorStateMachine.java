package com.dariuszdeoniziak.charades.statemachines.coordinator.app;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.ScreenNavigatorAttached;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.TerminateApp;
import com.dariuszdeoniziak.charades.utils.Optional;

import io.reactivex.rxjava3.core.Observable;

public interface AppCoordinatorStateMachine {

    // STATE
    Observable<AppCoordinatorState> state();
    interface DataReader {
        Optional<Destination> getDestination();
    }

    // EVENTS
    void onScreenHostAttached();

    // TRANSITION
    interface Transition {
        AppCoordinatorState onEvent(ScreenNavigatorAttached event);
        AppCoordinatorState onEvent(TerminateApp event);
    }

}
