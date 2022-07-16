package com.dariuszdeoniziak.charades.statemachines.categories.list;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.Event;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadingError;
import com.dariuszdeoniziak.charades.utils.Logger;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class CategoriesListStateMachineDispatcher implements CategoriesListStateMachine {

    private final Subject<Event<Transition, CategoriesListState>> eventStream;
    private final Observable<CategoriesListState> state;

    public CategoriesListStateMachineDispatcher(
            Logger logger
    ) {
        eventStream = PublishSubject.create();
        state = eventStream
                .scan(CategoriesListState.EMPTY_LIST, (currentState, event) -> {
                    logger.info("State: " + currentState + ", received Event: " + event);
                    return currentState.transition(event);
                });
    }

    @Override
    public Observable<CategoriesListState> state() {
        return state;
    }

    @Override
    public void onLoadList() {
        eventStream.onNext(LoadList.INSTANCE);
    }

    @Override
    public void onListLoaded(List<Category> categories) {
        eventStream.onNext(new ListLoaded(categories));
    }

    @Override
    public void onLoadingError(Throwable throwable) {
        eventStream.onNext(new LoadingError(throwable));
    }
}
