package com.dariuszdeoniziak.charades.statemachines.coordinator.navigation;

import com.dariuszdeoniziak.charades.navigators.Destination;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.DestinationDisplayed;
import com.dariuszdeoniziak.charades.statemachines.coordinator.navigation.events.NavigateToDestination;
import com.dariuszdeoniziak.charades.utils.Logger;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class DestinationCoordinatorStateMachineDispatcher implements DestinationCoordinatorStateMachine {

    private final Subject<Event<Transition, DestinationCoordinatorState>> eventStream;
    private final Observable<DestinationCoordinatorState> state;

    @Inject
    public DestinationCoordinatorStateMachineDispatcher(
            Logger logger
    ) {
        eventStream = PublishSubject.create();
        state = eventStream
                .scan(DestinationCoordinatorState.NO_DESTINATION, (currentState, event) -> {
                    logger.info("State: " + currentState + ", received Event: " + event);
                    return currentState.transition(event);
                });
    }

    @Override
    public Observable<DestinationCoordinatorState> state() {
        return state;
    }

    @Override
    public void onNavigateToDestination(Destination<?> destination) {
        eventStream.onNext(new NavigateToDestination(destination));
    }

    @Override
    public void onDestinationDisplayed(Destination<?> destination) {
        eventStream.onNext(new DestinationDisplayed(destination));
    }
}
