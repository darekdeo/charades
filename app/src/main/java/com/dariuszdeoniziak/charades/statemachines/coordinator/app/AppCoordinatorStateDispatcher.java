package com.dariuszdeoniziak.charades.statemachines.coordinator.app;

import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.coordinator.app.events.ScreenNavigatorAttached;
import com.dariuszdeoniziak.charades.utils.Logger;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class AppCoordinatorStateDispatcher implements AppCoordinatorStateMachine {

    private final Subject<Event<Transition, AppCoordinatorState>> eventStream;
    private final Observable<AppCoordinatorState> state;

    @Inject
    public AppCoordinatorStateDispatcher(
            Logger logger
    ) {
        eventStream = PublishSubject.create();
        state = eventStream
                .scan(AppCoordinatorState.STARTING, (currentState, event) -> {
                    logger.info("State: " + currentState + ", received Event: " + event);
                    return currentState.transition(event);
                });
    }
    @Override
    public Observable<AppCoordinatorState> state() {
        return state.hide();
    }

    @Override
    public void onScreenHostAttached() {
        eventStream.onNext(new ScreenNavigatorAttached());
    }
}
