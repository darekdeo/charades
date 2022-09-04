package com.dariuszdeoniziak.charades.statemachines.coordinator.app;

import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.ScreenNavigatorAttached;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.TerminateApp;

import io.reactivex.rxjava3.core.Observable;

public interface AppCoordinatorStateMachine {

    // STATE
    Observable<AppCoordinatorState> state();
    interface State extends com.dariuszdeoniziak.charades.statemachines.State<Event, AppCoordinatorState> {}

    // EVENTS
    interface Event extends com.dariuszdeoniziak.charades.statemachines.Event<AppCoordinatorStateMachine.Transition, AppCoordinatorState> {}
    void onScreenHostAttached();

    // TRANSITION
    interface Transition {
        AppCoordinatorState onEvent(ScreenNavigatorAttached event);
        AppCoordinatorState onEvent(TerminateApp event);
    }

}
