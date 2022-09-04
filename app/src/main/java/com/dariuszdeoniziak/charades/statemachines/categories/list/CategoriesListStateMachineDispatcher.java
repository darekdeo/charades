package com.dariuszdeoniziak.charades.statemachines.categories.list;

import com.dariuszdeoniziak.charades.data.models.Category;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.DeleteCategory;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.Error;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.ListLoaded;
import com.dariuszdeoniziak.charades.statemachines.categories.list.events.LoadList;
import com.dariuszdeoniziak.charades.utils.Logger;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class CategoriesListStateMachineDispatcher implements CategoriesListStateMachine {

    private final Subject<CategoriesListStateMachine.Event> eventStream;
    private final Observable<CategoriesListStateMachine.ResultState> state;

    public CategoriesListStateMachineDispatcher(
            Logger logger
    ) {
        eventStream = PublishSubject.create();
        state = eventStream
                .scan(CategoriesListState.defaultResultState(), (currentState, event) -> {
                    logger.info("State: " + currentState + ", received Event: " + event);
                    return currentState.state().transition(event);
                });
    }

    @Override
    public Observable<CategoriesListStateMachine.ResultState> state() {
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
    public void onDelete(Category category) {
        eventStream.onNext(new DeleteCategory(category));
    }

    @Override
    public void onError(Throwable throwable) {
        eventStream.onNext(new Error(throwable));
    }
}
